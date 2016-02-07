#!/bin/bash

source ./gradle.properties

_version=${version}

_gcm_base="/opt/gcm-server/"
_gcm_config_file=/etc/gcm-server/gcm-project.properties

function start_mysql_osx {
    sudo /usr/local/mysql/support-files/mysql.server start
}

function start_mysql_linux {
    sudo -E sh -c "service mysql start"
}

function install_mysql {
    sudo -E sh -c "apt-get install mysql-server-core-5.6"
}

function check_mysql {
    if [[ "$OSTYPE" == "linux-gnu" ]]; then
	result=$(pgrep mysql | wc -l);
        if [ ${result} -eq 0 ]; then
                read -p "mysql is down, would you like to start it ([y]/n):" yn
		case $yn in
			[Yy]* ) start_mysql_linux ; break;;
			[Nn]* ) echo "you can't proceed withuot having mysql up and running"
				exit;;
			* ) start_mysql_linux;;
		esac
        else
                echo "mysql is already running.."
        fi
    elif [[ "$OSTYPE" == "darwin"* ]]; then
	mysqladmin status
	result=$?
        if [ "${result}" -eq "0" ]; then
                echo "mysql service running..."
        else
                read -p "mysql is down, would you like to start it ([y]/n):" yn
                case $yn in
                        [Yy]* ) start_mysql_osx ; break;;
                        [Nn]* ) exit;;
                        * ) start_mysql_osx;;
                esac
        fi
    fi
}


function check_already_running {
        result=$(screen -ls | grep gcm-server | wc -l);
        if [ "${result}" -ne "0" ]; then
                echo "openbaton is already running.."
		exit;
        fi
}

function init {
    if [ ! -f ${_gcm_config_file} ]; then
        if [ $EUID != 0 ]; then
            echo "creating the directory and copying the file"
            sudo -E sh -c "mkdir /etc/nubomedia; cp ${_gcm_base}/src/main/resources/paas.properties ${_gcm_config_file}"
            #echo "copying the file, insert the administrator password" | sudo -kS cp ${_nubomedia_paas_base}/src/main/resources/paas.properties ${_nubomedia_config_file}
        else
            echo "creating the directory"
            mkdir /etc/nubomedia
            echo "copying the file"
            cp ${_gcm_base}/src/main/resources/gcm-project.properties ${_gcm_config_file}
        fi
    else
        echo "Properties file already exist"
    fi

    export dbchoice
    read -p "Do you want real persistency, not hsqldb [y/n]?" dbchoice
    case $dbchoice in

        [y]* ) install_mysql ; start_mysql_linux ; check_mysql ; break;;
        [n]* ) exit ;;
    esac
}


function start {

    if [ ! -d build/  ]
        then
            compile
    fi
    #check_mysql
    check_already_running
    if [ 0 -eq $? ]
        then
	    #screen -X eval "chdir $PWD"
	    screen -c screenrc -d -m -S gcm-server -t gcm-server java -jar "build/libs/gcm-server-$_version.jar" --spring.config.location=file:${_gcm_config_file}
	    #screen -c screenrc -r -p 0
    fi
}

function stop {
    if screen -list | grep "gcm-server"; then
	    screen -S gcm-server -p 0 -X stuff "exit$(printf \\r)"
    fi
}

function restart {
    kill
    start
}


function kill {
    if screen -list | grep "gcm-server"; then
	    screen -ls | grep gcm-server | cut -d. -f1 | awk '{print $1}' | xargs kill
    fi
}


function compile {
    ./gradlew build -x test
}

function tests {
    ./gradlew test
}

function clean {
    ./gradlew clean
}

function end {
    exit
}
function usage {
    echo -e "Gcm-Server\n"
    echo -e "Usage:\n\t ./gcm-server.sh [compile|start|stop|test|kill|clean]"
}

##
#   MAIN
##

if [ $# -eq 0 ]
   then
        usage
        exit 1
fi

declare -a cmds=($@)
for (( i = 0; i <  ${#cmds[*]}; ++ i ))
do
    case ${cmds[$i]} in
        "clean" )
            clean ;;
        "sc" )
            clean
            compile
            start ;;
        "start" )
            start ;;
        "stop" )
            stop ;;
        "restart" )
            restart ;;
        "compile" )
            compile ;;
        "kill" )
            kill ;;
        "test" )
            tests ;;
        * )
            usage
            end ;;
    esac
    if [[ $? -ne 0 ]];
    then
	    exit 1
    fi
done
