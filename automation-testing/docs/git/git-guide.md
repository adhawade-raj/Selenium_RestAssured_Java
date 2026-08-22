# Git Guide — automation-testing/docs/git/git-guide.md

This document provides step-by-step git workflows, commands, and explanations for common operations: init, clone, add, commit, push (single and all), remote setup, pull, fetch, merge, rebase, stash, conflict resolution, and raising PRs.

---

## Table of Contents

1. Initialize & add remote
2. Clone, fetch, pull
3. Add, commit, push (single file and all)
4. Branching and PR workflow
5. Pull & resolving conflicts
6. Stash: save & apply
7. Merge vs Rebase (with diff and commands)
8. Quick command reference table

---

## 1) Initialize & add remote

Step-by-step:

- Initialize a new repo locally:

  git init

- Check status:

  git status

- Add a remote (set URL):

  git remote add origin <REMOTE_URL>

- Verify remote:

  git remote -v

When to use: create local repo and link to GitHub/GitLab remote.


## 2) Clone, fetch, pull

- Clone remote repo (gets full repo):

  git clone <REMOTE_URL>

- Fetch updates (download remote refs but don’t merge):

  git fetch origin

- Pull (fetch + merge or rebase depending on config):

  git pull origin <branch>

Notes:
- Use git fetch when you want to inspect remote changes prior to merging.
- Use git pull --rebase to avoid merge commits if desired.


## 3) Add, commit, push (single file and all)

- Stage a single file:

  git add path/to/file

- Stage all changed files:

  git add -A
  or
  git add .

- Commit staged changes:

  git commit -m "Short descriptive message"

- Push current branch to origin (first time, set upstream):

  git push -u origin <branch>

- Push subsequent times:

  git push

To push a single file without staging others, use git add <file> then git commit as above.


## 4) Branching and raising a PR

- Create and switch to a new branch:

  git checkout -b feature/my-feature

- Work, stage, commit. Push branch:

  git push -u origin feature/my-feature

- On remote (GitHub/GitLab) create a Pull Request (PR) from feature/my-feature into target (e.g., master/main).

- Typical PR steps:
  - Push branch
  - Open remote UI, create PR, add reviewers, description
  - Address review comments on branch, push more commits
  - After approval merge via UI or merge locally and push merged branch


## 5) Pull & resolving conflicts

When pulling remote changes leads to conflicts:

1. git pull origin <branch>
2. If conflict occurs, Git will mark conflicted files. Inspect:
   git status
3. Open conflicted files and resolve conflict markers:
   <<<<<<< HEAD
   (your code)
   =======
   (their code)
   >>>>>>> origin/<branch>
4. After editing and resolving, stage resolved files:
   git add path/to/resolved-file
5. Continue merge by committing (if merge):
   git commit -m "Resolve merge conflicts"
   or if rebase was in progress:
   git rebase --continue
6. Push resolved branch:
   git push

If the push was rejected due to remote updates, fetch & rebase or merge remote changes:
- Option A (merge):
  git fetch origin
  git merge origin/<branch>
- Option B (rebase):
  git fetch origin
  git rebase origin/<branch>


## 6) Stash: save & apply

- Save current uncommitted changes:

  git stash save "WIP message"
  or (modern):
  git stash push -m "WIP message"

- List stashes:

  git stash list

- Apply last stash (keep stash):

  git stash apply

- Pop last stash (apply and remove):

  git stash pop

- Apply specific stash:

  git stash apply stash@{2}

- Drop a stash:

  git stash drop stash@{2}

- Clear all stashes:

  git stash clear

Use case: when pulling or switching branches but need to save local work to avoid conflicts.

Example workflow to pull while having local changes:

- Save work: git stash push -m "WIP"
- Pull updates: git pull --rebase origin <branch>
- Apply stash: git stash pop
- Resolve conflicts if any; commit and push


## 7) Merge vs Rebase (with diff and how-to)

What they do:

- Merge: creates a merge commit that combines histories. Keeps chronological commit history.
- Rebase: rewrites commits to appear on top of another base (linear history). Avoid rebasing public/shared branches.

Commands and examples:

- Merge:

  git checkout main
  git merge feature/my-feature

  This produces a merge commit if branches diverged.

- Rebase (move feature commits on top of main):

  git checkout feature/my-feature
  git fetch origin
  git rebase origin/main

  Resolve conflicts during rebase, then git rebase --continue. After rebase, force-push the branch (if it was pushed before):

  git push --force-with-lease origin feature/my-feature

When to prefer:
- Use merge for public branches to keep history intact.
- Use rebase for private feature branches to keep history linear before merging.


### Diffing before merge/rebase

- Show differences between branches:

  git fetch origin
  git diff origin/main..feature/my-feature

- Show commits that would be added by merging:

  git log --oneline origin/main..feature/my-feature


## 8) Quick command reference table

| Command | Purpose |
|---|---|
| git init | Initialize a new local repository |
| git clone <url> | Clone a remote repository locally |
| git remote add origin <url> | Add remote named origin |
| git remote -v | Show remote URLs |
| git status | Show working tree status |
| git add <file> | Stage single file |
| git add -A / git add . | Stage all changes |
| git commit -m "msg" | Commit staged changes |
| git branch | List branches |
| git checkout -b <branch> | Create and switch to branch |
| git checkout <branch> | Switch branch |
| git push -u origin <branch> | Push branch and set upstream |
| git push | Push current branch to its remote upstream |
| git fetch origin | Fetch remote updates without merging |
| git pull origin <branch> | Fetch + merge (or rebase) remote branch |
| git pull --rebase origin <branch> | Fetch + rebase to avoid merge commit |
| git merge <branch> | Merge given branch into current branch |
| git rebase <base> | Rebase current branch onto base |
| git rebase --continue | Continue rebase after resolving conflicts |
| git rebase --abort | Abort rebase and return to original state |
| git merge --abort | Abort merge and return to pre-merge state |
| git stash push -m "msg" | Save uncommitted changes to stash |
| git stash list | List saved stashes |
| git stash apply | Apply stash but keep it in list |
| git stash pop | Apply stash and remove from list |
| git log --oneline | Compact commit history |
| git diff | Show unstaged changes |
| git diff --staged | Show staged changes |
| git diff <branchA>..<branchB> | Show differences between branches |


## Examples: common workflows

1) Start repo, add remote, push all files:

- git init
- git add -A
- git commit -m "Initial commit"
- git remote add origin <url>
- git push -u origin main

2) Push only one file:

- git add path/to/file
- git commit -m "Add specific file"
- git push

3) Update from remote and handle conflicts using stash:

- git stash push -m "WIP"
- git pull --rebase origin main
- git stash pop
- Resolve conflicts if presented, git add <file>, git rebase --continue or git commit
- git push

4) Rebase feature branch onto main before creating PR:

- git checkout feature/my-feature
- git fetch origin
- git rebase origin/main
- resolve conflicts, git rebase --continue
- git push --force-with-lease origin feature/my-feature
- Create PR


## Notes & Best Practices

- Avoid git push --force on shared branches; prefer --force-with-lease if you must.
- Use descriptive commit messages.
- Keep feature branches small and focused.
- Prefer pull requests for code review; use the remote UI (GitHub/GitLab) to raise PRs.
- When in doubt, use git fetch and inspect diffs/logs before merging or rebasing.


---

If you want additional sections (examples for GitHub UI PR flow, CI checks, or GUI tools like SourceTree/VS Code), say which and the file will be updated.
