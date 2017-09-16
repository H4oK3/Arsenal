## A small script allow you to pipe the tcpdump from wifipineapple to local wireshark

- $1 : wlan0/wlan1
- $2 : tcpdump filter
- To make this work, u also need to add an entry for wifipineapple in ur /etc/hosts
  - Example Usage:
`./wirepipe.sh wlan0 "host 192.168.255.153 and not port 22"`

