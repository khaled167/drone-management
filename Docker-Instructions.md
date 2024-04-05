# For Docker installation please refer to

Linux https://docs.docker.com/desktop/install/linux-install

macOS https://docs.docker.com/desktop/install/mac-install

Windows https://docs.docker.com/desktop/install/windows-install

Check if docker is running

```shell
sudo systemctl status docker 
```

If not, then

```shell
sudo systemctl start docker
```

check if buildx is installed or not (usually installed with the installation of docker)

```shell
docker buildx version
```

If not installed, then

```shell
docker buildx install
```