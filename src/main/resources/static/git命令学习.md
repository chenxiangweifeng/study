# git 文件的几种状态
新创建的文件为 untracked(未追踪状态)
git add 之后就将该文件加入了 staged（暂存区）
git commit 文件进入 unmodified(不可修改状态)
最后 git push 将文件推送到远端仓库

# 更改当前项目git提交的用户名和邮箱
git config user.name "chenxiang"
git config user.email "1248195695@qq.com"
# 更改全局用户名和邮箱地址：
git config --global user.name "chenxiang"
git config --global user.email "1248195695@qq.com"
# 查看当前项目git户名和邮箱地址：
> git config user.name
> git config user.email
# 在IDEA commit的时候,右侧Author框里面可以选择作者

# 常用的git 操作命令
git status # 查看当前git 文件夹状态
git rm --cache xx # 删除暂存区文件
git push -u origin master # 向远程仓库推送本地变更
git log  # 查看提交历史
git log -num  # 查看最近几次提交的历史  q quit 退出历史查看
git diff # 比较当前工作区和暂存区的差异
git reset HEAD filename  # 移除已添加到暂存区文件
git reset HEAD^  # 移除已经commit内容
git tag +标签名  # 默认是给最近一次提交打标签





