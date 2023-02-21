## BudgetTrackerAndroidApp

1. Solution to fix this error
    "LF will be replaced by CRLF the next time Git touches it"

    
```bash
> git config core.autoCRLF
> true

> git config core.autoCRLF false

> git config core.autoCRLF
> false
```

2. Solution for this error
    "! [rejected]        main -> main (non-fast-forward)
       error: failed to push some refs to 'https://github.com/gitHubId/examplename.git'
       hint: its remote counterpart. Integrate the remote changes (e.g.
       hint: 'git pull ...') before pushing again.
       error: src refspec master does not match any"
       
```bash
> git pull --rebase origin main(or use master if your origin branch is master)

> git push origin main(or use master if your origin branch is master)
```
