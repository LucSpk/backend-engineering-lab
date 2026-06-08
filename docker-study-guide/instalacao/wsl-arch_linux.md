# Passo a passo para Instalar o docker no Arch linux (Usando WSL)

## Atualiza o sistema:
```bash
sudo pacman -Syu
```
Obs.: Colocar senha e apertar "Y" para confirmar a atualização.

## Instala o docker:
```bash
sudo pacman -S docker
```
Obs.: Colocar senha e apertar "Y" para confirmar a instalação.

## Iniciar o Serviço Docker
Após a instalação, inicie o daemon do Docker:
```bash
sudo systemctl start docker
```

Para fazer o Docker iniciar automaticamente com o sistema:
```bash
sudo systemctl enable docker
```

Verifique se está rodando:
```bash
docker ps
```

## Configurar Permissões de Usuário
Para rodar comandos do Docker sem a necessidade de digitar sudo todas as vezes, adicione o seu usuário atual ao grupo do Docker:
```bash
sudo usermod -aG docker $USER
newgrp docker
```
Após executar esse comando, você precisará sair e entrar novamente na sessão WSL para que as mudanças tenham efeito. Alternativamente, execute `newgrp docker` para ativar o grupo na sessão atual.


## Para parar o serviço do Docker:
```bash
sudo systemctl stop docker
```

## Para Impedir que o serviço do Docker inicie automaticamente com o sistema:
```bash
sudo systemctl disable docker
```
ou 
```bash
sudo systemctl disable docker.service
sudo systemctl disable docker.socket
```

Ambas as formas funcionam, mas há uma diferença:

sudo systemctl disable docker - desabilita o serviço principal, mas o docker.socket ainda pode ativar o daemon automaticamente quando você tentar usar Docker (ativação por socket).

sudo systemctl disable docker.service + sudo systemctl disable docker.socket - desabilita ambos, impedindo completamente a inicialização automática.