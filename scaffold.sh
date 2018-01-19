#!/bin/bash
mkdir $1
cp -R bootstrap/* $1
cd $1
sed -i '' "s/<name>/$1/" *.gradle
cd ..
