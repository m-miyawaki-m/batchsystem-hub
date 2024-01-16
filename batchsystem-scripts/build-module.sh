#!/bin/bash

# ディレクトリパスの変数定義
BASE_PATH=/home/vscode/github/batchsystem-hub/

# 引数からビルド対象の子モジュールを取得
MODULE_NAME=$1

# 子モジュールのディレクトリに移動
cd ${BASE_PATH}${MODULE_NAME}

# モジュールのビルド(テストスキップ)
if mvn clean package -DskipTests; then
    # ビルド成功時のJARファイルの移動
    mv ${BASE_PATH}${MODULE_NAME}/target/${MODULE_NAME}-1.0-SNAPSHOT.jar ${BASE_PATH}batchsystem-scripts/jar/
else
    echo "ビルドに失敗しました。"
    exit 1
fi