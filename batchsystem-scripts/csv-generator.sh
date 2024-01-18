#!/bin/bash

# ディレクトリパスの変数定義
BASE_PATH=/home/vscode/github/batchsystem-hub/

# 環境変数の設定
export FILE_PATH_TO_CSV_GENERATE=${BASE_PATH}csv/generate/

# クラスパスの設定
CLASSPATH=${BASE_PATH}batchsystem-lib/commons-csv.jar:${BASE_PATH}batchsystem-scripts/jar/batchsystem-csv-generator-1.0-SNAPSHOT.jar

# Java アプリケーションの実行
java -cp $CLASSPATH com.miyawaki.batchsystem.csvgenerator.App