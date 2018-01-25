.class public Lcom/ha0k3/helloworld/MainActivity;
.super Landroid/support/v7/app/AppCompatActivity;
.source "MainActivity.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# static fields
.field public static final Bind_MESSAGE:Ljava/lang/String; = "Bind_TAG"

.field private static final EXTERNAL_FILE_NAME:Ljava/lang/String; = "ext_file.txt"

.field public static final EXTRA_MESSAGE:Ljava/lang/String; = "com.ha0k3.helloworld.MESSAGE"

.field private static final Exception_TAG:Ljava/lang/String; = "Exception_TAG"

.field public static final INTERNAL_FILE:Ljava/lang/String; = "file.txt"

.field public static final MSG_PAYLOAD:Ljava/lang/String; = "MSGPAYLOAD"

.field public static final MYPREF:Ljava/lang/String; = "mypref"

.field private static final Message_TAG:Ljava/lang/String; = "Message_TAG"


# instance fields
.field private bindService:Landroid/widget/Button;

.field private connection:Landroid/content/ServiceConnection;

.field database:Landroid/database/sqlite/SQLiteDatabase;

.field private myBinder:Lcom/ha0k3/helloworld/MyService$MyBinder;

.field private startService:Landroid/widget/Button;

.field private stopService:Landroid/widget/Button;

.field private unbindService:Landroid/widget/Button;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 47
    const-string v0, "JniTest"

    invoke-static {v0}, Ljava/lang/System;->loadLibrary(Ljava/lang/String;)V

    .line 48
    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .prologue
    .line 26
    invoke-direct {p0}, Landroid/support/v7/app/AppCompatActivity;-><init>()V

    .line 49
    new-instance v0, Lcom/ha0k3/helloworld/MainActivity$1;

    invoke-direct {v0, p0}, Lcom/ha0k3/helloworld/MainActivity$1;-><init>(Lcom/ha0k3/helloworld/MainActivity;)V

    iput-object v0, p0, Lcom/ha0k3/helloworld/MainActivity;->connection:Landroid/content/ServiceConnection;

    return-void
.end method

.method static synthetic access$000(Lcom/ha0k3/helloworld/MainActivity;)Lcom/ha0k3/helloworld/MyService$MyBinder;
    .locals 1
    .param p0, "x0"    # Lcom/ha0k3/helloworld/MainActivity;

    .prologue
    .line 26
    iget-object v0, p0, Lcom/ha0k3/helloworld/MainActivity;->myBinder:Lcom/ha0k3/helloworld/MyService$MyBinder;

    return-object v0
.end method

.method static synthetic access$002(Lcom/ha0k3/helloworld/MainActivity;Lcom/ha0k3/helloworld/MyService$MyBinder;)Lcom/ha0k3/helloworld/MyService$MyBinder;
    .locals 0
    .param p0, "x0"    # Lcom/ha0k3/helloworld/MainActivity;
    .param p1, "x1"    # Lcom/ha0k3/helloworld/MyService$MyBinder;

    .prologue
    .line 26
    iput-object p1, p0, Lcom/ha0k3/helloworld/MainActivity;->myBinder:Lcom/ha0k3/helloworld/MyService$MyBinder;

    return-object p1
.end method

.method private getFile()Ljava/io/File;
    .locals 3

    .prologue
    .line 95
    new-instance v0, Ljava/io/File;

    invoke-static {}, Landroid/os/Environment;->getExternalStorageDirectory()Ljava/io/File;

    move-result-object v1

    const-string v2, "ext_file.txt"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    return-object v0
.end method


# virtual methods
.method public ViewJniMessage(Landroid/view/View;)V
    .locals 2
    .param p1, "view"    # Landroid/view/View;

    .prologue
    .line 221
    invoke-virtual {p0}, Lcom/ha0k3/helloworld/MainActivity;->helloJni()Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x0

    invoke-static {p0, v0, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 222
    return-void
.end method

.method public delete_internal_ext_file(Landroid/view/View;)V
    .locals 6
    .param p1, "view"    # Landroid/view/View;

    .prologue
    const/4 v5, 0x0

    .line 195
    new-instance v2, Ljava/io/File;

    invoke-virtual {p0}, Lcom/ha0k3/helloworld/MainActivity;->getFilesDir()Ljava/io/File;

    move-result-object v3

    const-string v4, "file.txt"

    invoke-direct {v2, v3, v4}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 196
    .local v2, "file":Ljava/io/File;
    invoke-direct {p0}, Lcom/ha0k3/helloworld/MainActivity;->getFile()Ljava/io/File;

    move-result-object v1

    .line 199
    .local v1, "extfile":Ljava/io/File;
    invoke-virtual {v2}, Ljava/io/File;->exists()Z

    move-result v3

    if-eqz v3, :cond_0

    .line 200
    const-string v3, "file.txt"

    invoke-virtual {p0, v3}, Lcom/ha0k3/helloworld/MainActivity;->deleteFile(Ljava/lang/String;)Z

    .line 201
    const-string v3, "File deleted"

    invoke-static {p0, v3, v5}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v3

    invoke-virtual {v3}, Landroid/widget/Toast;->show()V

    .line 208
    :goto_0
    :try_start_0
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v3

    if-eqz v3, :cond_1

    .line 210
    invoke-virtual {v1}, Ljava/io/File;->delete()Z

    .line 211
    const-string v3, "External File deleted"

    const/4 v4, 0x0

    invoke-static {p0, v3, v4}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v3

    invoke-virtual {v3}, Landroid/widget/Toast;->show()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 219
    :goto_1
    return-void

    .line 203
    :cond_0
    const-string v3, "File doesn\'t exist"

    invoke-static {p0, v3, v5}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v3

    invoke-virtual {v3}, Landroid/widget/Toast;->show()V

    goto :goto_0

    .line 213
    :cond_1
    :try_start_1
    const-string v3, "External File doesn\'t exist"

    const/4 v4, 0x0

    invoke-static {p0, v3, v4}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v3

    invoke-virtual {v3}, Landroid/widget/Toast;->show()V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_1

    .line 215
    :catch_0
    move-exception v0

    .line 216
    .local v0, "e":Ljava/lang/Exception;
    const-string v3, "Exception_TAG"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "file delete failed: "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 217
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    goto :goto_1
.end method

.method public native helloJni()Ljava/lang/String;
.end method

.method public onClick(Landroid/view/View;)V
    .locals 5
    .param p1, "v"    # Landroid/view/View;

    .prologue
    .line 128
    invoke-virtual {p1}, Landroid/view/View;->getId()I

    move-result v3

    packed-switch v3, :pswitch_data_0

    .line 148
    :goto_0
    return-void

    .line 130
    :pswitch_0
    new-instance v1, Landroid/content/Intent;

    const-class v3, Lcom/ha0k3/helloworld/MyService;

    invoke-direct {v1, p0, v3}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 131
    .local v1, "startIntent":Landroid/content/Intent;
    invoke-virtual {p0, v1}, Lcom/ha0k3/helloworld/MainActivity;->startService(Landroid/content/Intent;)Landroid/content/ComponentName;

    goto :goto_0

    .line 134
    .end local v1    # "startIntent":Landroid/content/Intent;
    :pswitch_1
    new-instance v2, Landroid/content/Intent;

    const-class v3, Lcom/ha0k3/helloworld/MyService;

    invoke-direct {v2, p0, v3}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 135
    .local v2, "stopIntent":Landroid/content/Intent;
    invoke-virtual {p0, v2}, Lcom/ha0k3/helloworld/MainActivity;->stopService(Landroid/content/Intent;)Z

    goto :goto_0

    .line 138
    .end local v2    # "stopIntent":Landroid/content/Intent;
    :pswitch_2
    new-instance v0, Landroid/content/Intent;

    const-class v3, Lcom/ha0k3/helloworld/MyService;

    invoke-direct {v0, p0, v3}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 139
    .local v0, "bindIntent":Landroid/content/Intent;
    const-string v3, "Bind_TAG"

    const-string v4, "test_file_name"

    invoke-virtual {v0, v3, v4}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 140
    iget-object v3, p0, Lcom/ha0k3/helloworld/MainActivity;->connection:Landroid/content/ServiceConnection;

    const/4 v4, 0x1

    invoke-virtual {p0, v0, v3, v4}, Lcom/ha0k3/helloworld/MainActivity;->bindService(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z

    goto :goto_0

    .line 143
    .end local v0    # "bindIntent":Landroid/content/Intent;
    :pswitch_3
    iget-object v3, p0, Lcom/ha0k3/helloworld/MainActivity;->connection:Landroid/content/ServiceConnection;

    invoke-virtual {p0, v3}, Lcom/ha0k3/helloworld/MainActivity;->unbindService(Landroid/content/ServiceConnection;)V

    goto :goto_0

    .line 128
    nop

    :pswitch_data_0
    .packed-switch 0x7f0b0061
        :pswitch_0
        :pswitch_1
        :pswitch_2
        :pswitch_3
    .end packed-switch
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 7
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    const/4 v6, 0x0

    .line 64
    invoke-super {p0, p1}, Landroid/support/v7/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V

    .line 65
    const v4, 0x7f04001c

    invoke-virtual {p0, v4}, Lcom/ha0k3/helloworld/MainActivity;->setContentView(I)V

    .line 67
    new-instance v0, Lcom/ha0k3/helloworld/database/DBHelper;

    invoke-direct {v0, p0}, Lcom/ha0k3/helloworld/database/DBHelper;-><init>(Landroid/content/Context;)V

    .line 68
    .local v0, "dbHelper":Landroid/database/sqlite/SQLiteOpenHelper;
    invoke-virtual {v0}, Landroid/database/sqlite/SQLiteOpenHelper;->getWritableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v4

    iput-object v4, p0, Lcom/ha0k3/helloworld/MainActivity;->database:Landroid/database/sqlite/SQLiteDatabase;

    .line 69
    const-string v4, "Database acquired"

    invoke-static {p0, v4, v6}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v4

    invoke-virtual {v4}, Landroid/widget/Toast;->show()V

    .line 71
    const v4, 0x7f0b0061

    invoke-virtual {p0, v4}, Lcom/ha0k3/helloworld/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v4

    check-cast v4, Landroid/widget/Button;

    iput-object v4, p0, Lcom/ha0k3/helloworld/MainActivity;->startService:Landroid/widget/Button;

    .line 72
    const v4, 0x7f0b0062

    invoke-virtual {p0, v4}, Lcom/ha0k3/helloworld/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v4

    check-cast v4, Landroid/widget/Button;

    iput-object v4, p0, Lcom/ha0k3/helloworld/MainActivity;->stopService:Landroid/widget/Button;

    .line 73
    const v4, 0x7f0b0063

    invoke-virtual {p0, v4}, Lcom/ha0k3/helloworld/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v4

    check-cast v4, Landroid/widget/Button;

    iput-object v4, p0, Lcom/ha0k3/helloworld/MainActivity;->bindService:Landroid/widget/Button;

    .line 74
    const v4, 0x7f0b0064

    invoke-virtual {p0, v4}, Lcom/ha0k3/helloworld/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v4

    check-cast v4, Landroid/widget/Button;

    iput-object v4, p0, Lcom/ha0k3/helloworld/MainActivity;->unbindService:Landroid/widget/Button;

    .line 76
    iget-object v4, p0, Lcom/ha0k3/helloworld/MainActivity;->startService:Landroid/widget/Button;

    invoke-virtual {v4, p0}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 77
    iget-object v4, p0, Lcom/ha0k3/helloworld/MainActivity;->stopService:Landroid/widget/Button;

    invoke-virtual {v4, p0}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 78
    iget-object v4, p0, Lcom/ha0k3/helloworld/MainActivity;->bindService:Landroid/widget/Button;

    invoke-virtual {v4, p0}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 79
    iget-object v4, p0, Lcom/ha0k3/helloworld/MainActivity;->unbindService:Landroid/widget/Button;

    invoke-virtual {v4, p0}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 81
    const-string v4, "mypref"

    invoke-virtual {p0, v4, v6}, Lcom/ha0k3/helloworld/MainActivity;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v3

    .line 82
    .local v3, "prefs":Landroid/content/SharedPreferences;
    const-string v4, "MSGPAYLOAD"

    const-string v5, "KEY_NOT_FOUND"

    invoke-interface {v3, v4, v5}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 84
    .local v2, "msg":Ljava/lang/String;
    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_0

    .line 85
    const v4, 0x7f0b0066

    invoke-virtual {p0, v4}, Lcom/ha0k3/helloworld/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/EditText;

    .line 86
    .local v1, "et":Landroid/widget/EditText;
    invoke-virtual {v1, v2}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    .line 87
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "Message retrieved from shared_pref as : "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {p0, v4, v6}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v4

    invoke-virtual {v4}, Landroid/widget/Toast;->show()V

    .line 92
    .end local v1    # "et":Landroid/widget/EditText;
    :goto_0
    return-void

    .line 89
    :cond_0
    const-string v4, "Msg is blank"

    invoke-static {p0, v4, v6}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v4

    invoke-virtual {v4}, Landroid/widget/Toast;->show()V

    goto :goto_0
.end method

.method public sendMessage(Landroid/view/View;)V
    .locals 6
    .param p1, "view"    # Landroid/view/View;

    .prologue
    .line 99
    new-instance v2, Landroid/content/Intent;

    const-class v4, Lcom/ha0k3/helloworld/DisplayMessageActivity;

    invoke-direct {v2, p0, v4}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 100
    .local v2, "intent":Landroid/content/Intent;
    const v4, 0x7f0b0066

    invoke-virtual {p0, v4}, Lcom/ha0k3/helloworld/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/EditText;

    .line 101
    .local v0, "editText":Landroid/widget/EditText;
    invoke-virtual {v0}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v3

    .line 102
    .local v3, "message":Ljava/lang/String;
    const-string v4, "Message_TAG"

    invoke-static {v4, v3}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 104
    const-string v4, "mypref"

    const/4 v5, 0x0

    invoke-virtual {p0, v4, v5}, Lcom/ha0k3/helloworld/MainActivity;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v4

    invoke-interface {v4}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v1

    .line 106
    .local v1, "editor":Landroid/content/SharedPreferences$Editor;
    const-string v4, "MSGPAYLOAD"

    invoke-interface {v1, v4, v3}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 107
    invoke-interface {v1}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 110
    const-string v4, "com.ha0k3.helloworld.MESSAGE"

    invoke-virtual {v2, v4, v3}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 111
    invoke-virtual {p0, v2}, Lcom/ha0k3/helloworld/MainActivity;->startActivity(Landroid/content/Intent;)V

    .line 113
    return-void
.end method

.method public start_export(Landroid/view/View;)V
    .locals 4
    .param p1, "view"    # Landroid/view/View;

    .prologue
    .line 117
    const-string v2, "Trying to start export activity!"

    const/4 v3, 0x1

    invoke-static {p0, v2, v3}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v2

    .line 118
    invoke-virtual {v2}, Landroid/widget/Toast;->show()V

    .line 119
    new-instance v1, Landroid/content/Intent;

    invoke-direct {v1}, Landroid/content/Intent;-><init>()V

    .line 120
    .local v1, "intent":Landroid/content/Intent;
    new-instance v0, Landroid/content/ComponentName;

    const-string v2, "com.nccgroup.johnnyfive"

    const-string v3, "com.nccgroup.johnnyfive.AdminActivity"

    invoke-direct {v0, v2, v3}, Landroid/content/ComponentName;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    .line 122
    .local v0, "cn":Landroid/content/ComponentName;
    invoke-virtual {v1, v0}, Landroid/content/Intent;->setComponent(Landroid/content/ComponentName;)Landroid/content/Intent;

    .line 123
    invoke-virtual {p0, v1}, Lcom/ha0k3/helloworld/MainActivity;->startActivity(Landroid/content/Intent;)V

    .line 124
    return-void
.end method

.method public write_to_internal_ext_file(Landroid/view/View;)V
    .locals 10
    .param p1, "view"    # Landroid/view/View;

    .prologue
    .line 152
    const v7, 0x7f0b0066

    invoke-virtual {p0, v7}, Lcom/ha0k3/helloworld/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/EditText;

    .line 153
    .local v1, "et":Landroid/widget/EditText;
    invoke-virtual {v1}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v7

    invoke-virtual {v7}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v6

    .line 154
    .local v6, "msg":Ljava/lang/String;
    const/4 v4, 0x0

    .line 156
    .local v4, "fileOutputStream":Ljava/io/FileOutputStream;
    new-instance v3, Ljava/io/File;

    const-string v7, "file.txt"

    invoke-direct {v3, v7}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 157
    .local v3, "file":Ljava/io/File;
    invoke-direct {p0}, Lcom/ha0k3/helloworld/MainActivity;->getFile()Ljava/io/File;

    move-result-object v2

    .line 159
    .local v2, "extfile":Ljava/io/File;
    const-string v7, "Message_TAG"

    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    const-string v9, "exfile path: "

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v2}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-static {v7, v8}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 162
    :try_start_0
    const-string v7, "file.txt"

    const/4 v8, 0x0

    invoke-virtual {p0, v7, v8}, Lcom/ha0k3/helloworld/MainActivity;->openFileOutput(Ljava/lang/String;I)Ljava/io/FileOutputStream;

    move-result-object v4

    .line 163
    invoke-virtual {v6}, Ljava/lang/String;->getBytes()[B

    move-result-object v7

    invoke-virtual {v4, v7}, Ljava/io/FileOutputStream;->write([B)V

    .line 164
    const-string v7, "Saving the text to file..."

    const/4 v8, 0x0

    invoke-static {p0, v7, v8}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v7

    invoke-virtual {v7}, Landroid/widget/Toast;->show()V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 170
    :try_start_1
    invoke-virtual {v4}, Ljava/io/FileOutputStream;->close()V
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_0

    move-object v5, v4

    .line 179
    .end local v4    # "fileOutputStream":Ljava/io/FileOutputStream;
    .local v5, "fileOutputStream":Ljava/io/FileOutputStream;
    :goto_0
    :try_start_2
    new-instance v4, Ljava/io/FileOutputStream;

    invoke-direct {v4, v2}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_5
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    .line 180
    .end local v5    # "fileOutputStream":Ljava/io/FileOutputStream;
    .restart local v4    # "fileOutputStream":Ljava/io/FileOutputStream;
    :try_start_3
    invoke-virtual {v6}, Ljava/lang/String;->getBytes()[B

    move-result-object v7

    invoke-virtual {v4, v7}, Ljava/io/FileOutputStream;->write([B)V

    .line 181
    const-string v7, "Saving the text to file..."

    const/4 v8, 0x0

    invoke-static {p0, v7, v8}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v7

    invoke-virtual {v7}, Landroid/widget/Toast;->show()V
    :try_end_3
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_3} :catch_8
    .catchall {:try_start_3 .. :try_end_3} :catchall_2

    .line 187
    :try_start_4
    invoke-virtual {v4}, Ljava/io/FileOutputStream;->close()V
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_4

    .line 192
    :goto_1
    return-void

    .line 171
    :catch_0
    move-exception v0

    .line 172
    .local v0, "e":Ljava/io/IOException;
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    move-object v5, v4

    .line 174
    .end local v4    # "fileOutputStream":Ljava/io/FileOutputStream;
    .restart local v5    # "fileOutputStream":Ljava/io/FileOutputStream;
    goto :goto_0

    .line 165
    .end local v0    # "e":Ljava/io/IOException;
    .end local v5    # "fileOutputStream":Ljava/io/FileOutputStream;
    .restart local v4    # "fileOutputStream":Ljava/io/FileOutputStream;
    :catch_1
    move-exception v0

    .line 166
    .restart local v0    # "e":Ljava/io/IOException;
    :try_start_5
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    .line 167
    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    const-string v8, "Exception: "

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v0}, Ljava/io/IOException;->getMessage()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    const/4 v8, 0x0

    invoke-static {p0, v7, v8}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v7

    invoke-virtual {v7}, Landroid/widget/Toast;->show()V
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    .line 170
    :try_start_6
    invoke-virtual {v4}, Ljava/io/FileOutputStream;->close()V
    :try_end_6
    .catch Ljava/io/IOException; {:try_start_6 .. :try_end_6} :catch_2

    move-object v5, v4

    .line 173
    .end local v4    # "fileOutputStream":Ljava/io/FileOutputStream;
    .restart local v5    # "fileOutputStream":Ljava/io/FileOutputStream;
    goto :goto_0

    .line 171
    .end local v5    # "fileOutputStream":Ljava/io/FileOutputStream;
    .restart local v4    # "fileOutputStream":Ljava/io/FileOutputStream;
    :catch_2
    move-exception v0

    .line 172
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    move-object v5, v4

    .line 174
    .end local v4    # "fileOutputStream":Ljava/io/FileOutputStream;
    .restart local v5    # "fileOutputStream":Ljava/io/FileOutputStream;
    goto :goto_0

    .line 169
    .end local v0    # "e":Ljava/io/IOException;
    .end local v5    # "fileOutputStream":Ljava/io/FileOutputStream;
    .restart local v4    # "fileOutputStream":Ljava/io/FileOutputStream;
    :catchall_0
    move-exception v7

    .line 170
    :try_start_7
    invoke-virtual {v4}, Ljava/io/FileOutputStream;->close()V
    :try_end_7
    .catch Ljava/io/IOException; {:try_start_7 .. :try_end_7} :catch_3

    .line 173
    :goto_2
    throw v7

    .line 171
    :catch_3
    move-exception v0

    .line 172
    .restart local v0    # "e":Ljava/io/IOException;
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_2

    .line 188
    .end local v0    # "e":Ljava/io/IOException;
    :catch_4
    move-exception v0

    .line 189
    .restart local v0    # "e":Ljava/io/IOException;
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_1

    .line 182
    .end local v0    # "e":Ljava/io/IOException;
    .end local v4    # "fileOutputStream":Ljava/io/FileOutputStream;
    .restart local v5    # "fileOutputStream":Ljava/io/FileOutputStream;
    :catch_5
    move-exception v0

    move-object v4, v5

    .line 183
    .end local v5    # "fileOutputStream":Ljava/io/FileOutputStream;
    .restart local v0    # "e":Ljava/io/IOException;
    .restart local v4    # "fileOutputStream":Ljava/io/FileOutputStream;
    :goto_3
    :try_start_8
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    .line 184
    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    const-string v8, "Exception: "

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v0}, Ljava/io/IOException;->getMessage()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    const/4 v8, 0x0

    invoke-static {p0, v7, v8}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v7

    invoke-virtual {v7}, Landroid/widget/Toast;->show()V
    :try_end_8
    .catchall {:try_start_8 .. :try_end_8} :catchall_2

    .line 187
    :try_start_9
    invoke-virtual {v4}, Ljava/io/FileOutputStream;->close()V
    :try_end_9
    .catch Ljava/io/IOException; {:try_start_9 .. :try_end_9} :catch_6

    goto :goto_1

    .line 188
    :catch_6
    move-exception v0

    .line 189
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_1

    .line 186
    .end local v0    # "e":Ljava/io/IOException;
    .end local v4    # "fileOutputStream":Ljava/io/FileOutputStream;
    .restart local v5    # "fileOutputStream":Ljava/io/FileOutputStream;
    :catchall_1
    move-exception v7

    move-object v4, v5

    .line 187
    .end local v5    # "fileOutputStream":Ljava/io/FileOutputStream;
    .restart local v4    # "fileOutputStream":Ljava/io/FileOutputStream;
    :goto_4
    :try_start_a
    invoke-virtual {v4}, Ljava/io/FileOutputStream;->close()V
    :try_end_a
    .catch Ljava/io/IOException; {:try_start_a .. :try_end_a} :catch_7

    .line 190
    :goto_5
    throw v7

    .line 188
    :catch_7
    move-exception v0

    .line 189
    .restart local v0    # "e":Ljava/io/IOException;
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_5

    .line 186
    .end local v0    # "e":Ljava/io/IOException;
    :catchall_2
    move-exception v7

    goto :goto_4

    .line 182
    :catch_8
    move-exception v0

    goto :goto_3
.end method
