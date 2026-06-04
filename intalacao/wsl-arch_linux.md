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

## Configurar Permissões de Usuário
Para rodar comandos do Docker sem a necessidade de digitar sudo todas as vezes, adicione o seu usuário atual ao grupo do Docker:
```bash
sudo usermod -aG docker $USER
```
Após executar esse comando, é necessário sair e entrar novamente na sessão para que as mudanças tenham efeito