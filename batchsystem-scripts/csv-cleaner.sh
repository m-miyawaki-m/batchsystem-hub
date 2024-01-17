#!/bin/bash

# ディレクトリパスの変数定義
BASE_PATH=/home/vscode/github/batchsystem-hub/

# ディレクトリパスを配列として定義
directories=("${BASE_PATH}csv/import/" "${BASE_PATH}csv/export/" "${BASE_PATH}csv/generate/")

# 環境変数に設定
export BASE_PATH="${BASE_PATH}"
export CSV_DIRECTORIES="${directories[@]}"

# クラスパスの設定
CLASSPATH=${BASE_PATH}batchsystem-lib/commons-csv.jar:${BASE_PATH}batchsystem-lib/log4j-api-2.20.0.jar:${BASE_PATH}batchsystem-lib/log4j-core-2.19.0.jar:${BASE_PATH}batchsystem-scripts/jar/batchsystem-csv-cleaner-1.0-SNAPSHOT.jar

# # Java アプリケーションの実行
java -cp $CLASSPATH com.miyawaki.batchsystem.csvcleaner.App
# java -jar ${BASE_PATH}batchsystem-scripts/jar/batchsystem-csv-cleaner-1.0-SNAPSHOT.jar