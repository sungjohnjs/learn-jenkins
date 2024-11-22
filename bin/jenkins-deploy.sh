#!/bin/bash

git add .
git commit -m "commit before deploying with jenkins"
curl -u johnsungjs:Jomega123+ http://10.190.7.21:8020/job/learn-jenkins-pipeline/build?token=rahasia

