#!/bin/bash

SCRIPTPATH="$( cd -- "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )"

docker run -it -v $SCRIPTPATH/volume:/benchmarks:Z -v $SCRIPTPATH/tmp/reports:/tmp/reports:Z --network=host quay.io/hyperfoil/hyperfoil cli

docker run --rm -it -v C:\Users\BartoszKuziemski\IdeaProjects\master-thesis-spring\hyperfoil:/hyperfoil quay.io/hyperfoil/hyperfoil cli

docker run -p 8080:8083 --rm -it -v C:\Users\BartoszKuziemski\IdeaProjects\master-thesis-spring\hyperfoil:/hyperfoil quay.io/hyperfoil/hyperfoil cli

docker run --rm -it -v C:\Users\BartoszKuziemski\IdeaProjects\master-thesis-spring\hyperfoil:/hyperfoil --network=host quay.io/hyperfoil/hyperfoil cli
