### 新しい親モジュールが正しく設定され、ローカルの Maven リポジトリにインストールされていることを確認するには、以下の手順を実行します：

1. 親モジュールのプロジェクトディレクトリに移動します。
2. ターミナルで mvn clean install コマンドを実行します。これにより、親モジュールがビルドされ、ローカルの Maven リポジトリにインストールされます。
3. ビルドが成功したことを確認します。成功した場合、ターミナルの出力には "BUILD SUCCESS" と表示されます。
4. 子モジュールのプロジェクトディレクトリに戻り、mvn clean compile コマンドを実行します。これにより、子モジュールが新しい親モジュールを使用してビルドされます。
5. ビルドが成功したことを確認します。成功した場合、ターミナルの出力には "BUILD SUCCESS" と表示されます。
6. これらの手順を実行することで、新しい親モジュールが正しく設定され、ローカルの Maven リポジトリにインストールされていることを確認できます。

### wsl用のsetting.json例（windows上とはjdkの設定を変更する必要がある）
```
{
    "java.configuration.updateBuildConfiguration": "automatic",
    "java.home": "/usr/lib/jvm/java-17-openjdk-amd64"
}
```



### lombokを認識しない場合の対策
1. VSCODEの拡張機能のインストール
2. mavenフォルダを検索しsetting.jsonに記載
```
find ~/.m2 -name 'lombok*.jar'
```