#!/bin/bash

# ディレクトリパスの変数定義
BASE_PATH=/home/vscode/github/batchsystem-hub/

# 環境変数の設定
export FILE_PATH_TO_CSV_GENERATE=${BASE_PATH}csv/generate/

# クラスパスの設定
# CLASSPATH=${BASE_PATH}batchsystem-lib/commons-csv.jar:${BASE_PATH}batchsystem-scripts/jar/batchsystem-csv-generator-1.0-SNAPSHOT.jar
CLASSPATH=${BASE_PATH}batchsystem-lib/commons-csv.jar:\
${BASE_PATH}batchsystem-scripts/jar/batchsystem-csv-generator-1.0-SNAPSHOT.jar


# JARファイルのパスの設定
JAR_FILE=${BASE_PATH}batchsystem-scripts/jar/batchsystem-csv-generator-1.0-SNAPSHOT.jar

###########################################
# ログ出力
###########################################

# ログファイルのパス
LOG_FILE=${BASE_PATH}/logs/sripts.log

# ログ出力関数
log_message() {
    echo "[$(date)] - Script: $0, JAR: $JAR_FILE, Status: $2, Execution Time: $3 seconds" >> "$LOG_FILE"
    message="[$(date)] - Script: $0, JAR: $JAR_FILE, Status: $2, Execution Time: $3 seconds"
    echo $message
    echo $message >> "$LOG_FILE"
}

# 実行開始時刻
start_time=$(date +%s)

# Java アプリケーションの実行
java -cp $CLASSPATH com.miyawaki.batchsystem.csvgenerator.App
status=$?

# 実行終了時刻
end_time=$(date +%s)

# 実行時間の計算
execution_time=$((end_time - start_time))

# 結果のログ記録
if [ $status -eq 0 ]; then
    log_message "Success" "$execution_time"
else
    log_message "Failure" "$execution_time"
fi