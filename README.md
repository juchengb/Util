jar 檔執行報錯
雖然有內建的Zip壓縮功能，但缺少幾種壓縮功能(例如Zip64格式等的標準Zip加密和AES加密)，基於此而有 Zip4j 套件的產生。
<dependency>
	<groupId>net.lingala.zip4j</groupId>
	<artifactId>zip4j</artifactId>
	<version>2.11.5</version>
</dependency>