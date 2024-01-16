### wsl環境で実行しているためwindowsの.m2にシンボリックリンクを作成する（管理者権限）

```console
ln -s /mnt/c/Users/miyaw/.m2/repository/org/apache/commons/commons-csv/1.9.0/commons-csv-1.9.0.jar /home/vscode/github/batchsystem-hub/batchsystem-lib/commons-csv.jar
ln -s /mnt/c/Users/miyaw/.m2/repository/org/apache/logging/log4j/log4j-api/2.20.0/log4j-api-2.20.0.jar /home/vscode/github/batchsystem-hub/batchsystem-lib/log4j-api-2.20.0.jar
ln -s /mnt/c/Users/miyaw/.m2/repository/org/apache/logging/log4j/log4j-core/2.19.0/log4j-core-2.19.0.jar /home/vscode/github/batchsystem-hub/batchsystem-lib/log4j-core-2.19.0.jar
```