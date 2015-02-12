#!/bin/bash
# -*- ENCODING: UTF-8 -*-
sudo apt-get update
sudo apt-get install -y openjdk-7-jre  libcups2-dev libcupsimage2-dev openssh-server openssh-client lsb-core
java -jar dist/PARE.jar instalador
exit
