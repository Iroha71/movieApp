# コーディング規約

## 方針
- クラス名とメソッド名は基本的にクラス図を参照
- 新しいクラスやメソッドを追加するときは要相談

## クラス
### コントローラ
- 命名:`名称+Controller`
    - 例:`LoginController`、`LoginStartController`

### モデル
- 命名:`名称+Model`
    - 例:`UserModel`

### DAO
- 命名:`テーブル名+DAO`
    - 例:`UserDAO`

## メソッド
- 命名:`動詞+名詞`
    - 例:`getMovie`
- コメントなしでもわかる名前にする

## 変数
- わかりやすい名前にする
    - ○`userName`
    - ×`a1`
- すべてキャメルケースにすること
    - ○`userName`
    - ×`user_name`,`user-name`,`UserName`

## その他
### git
- メッセージは`接頭語: 変更内容`
    - 例:`add: ログイン処理`,`fix: 表示バグ修正`
- 接頭語の種類
    - `add:`
        - 機能やファイルを追加したとき
    - `update:`
        - 今ある機能の内容を変更、削除したとき
    - `fix:`
        - バグや不具合の修正
    - `refactor:`
        - コードのリファクタリング
- gitのコミットは細かい単位で行う

### コメント
- わかりにくい処理にはコメント推奨