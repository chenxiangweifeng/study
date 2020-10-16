# git 文件的几种状态
新创建的文件为 untracked(未追踪状态)
git add 之后就将该文件加入了 staged（暂存区）
git commit 文件进入 unmodified(不可修改状态)
最后 git push 将文件推送到远端仓库

# 常用的git 操作命令
git status # 查看当前git 文件夹状态
git rm --cache xx # 删除暂存区文件
git push -u origin master # 向远程仓库推送本地变更
