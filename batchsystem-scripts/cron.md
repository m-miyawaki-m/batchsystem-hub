

```console:cron編集
crontab -e
```

```batch:crontab
# 1時間に1回実行するよう設定

0 * * * * /home/vscode/github/batchsystem-hub/batchsystem-scripts/generator.sh

30 * * * * /home/vscode/github/batchsystem-hub/batchsystem-scripts/csv-cleaner.sh
```