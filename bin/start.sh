#!/bin/bash

nohup java -jar ./target/learn_jenkins-0.0.1.jar >log/app.log 2>&1 &
echo \$! > pid.file
