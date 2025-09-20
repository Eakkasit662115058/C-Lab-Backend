FROM ubuntu:latest
LABEL authors="eakka"

ENTRYPOINT ["top", "-b"]