# Convert webm to mp3

```
find . -name "*.webm" -exec ffmpeg -i '{}' '{}.mp3' \;
```

