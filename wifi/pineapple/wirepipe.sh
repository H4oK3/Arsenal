if [[ -n "$2"  ]]
then
    options="$2"
else
    options="not port 22"
fi
if [[ -n "$1"  ]]
then
	ssh root@wifipineapple tcpdump -U -s0 -i "$1" -w '$options' - | wireshark -k -i -
else
	echo "[+]USAGE: ./wirepipe.sh <wlan0|wlan1>"
fi