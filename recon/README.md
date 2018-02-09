##Recon-ng cheat sheet:
- Add keys:
~~~
show keys
keys add test_api api_key
~~~

- Create/select workspace
~~~
workspace list
workspaces select name
~~~

- Enumerate domain host
~~~
add domains target.com
use recon/domains-hosts/certificate_transparency
use recon/domains-hosts/hackertarget
use recon/domains-hosts/threatcrowd_api
use recon/domains-hosts/bing_domain_web
use recon/domains-hosts/google_site_web
use recon/domains-hosts/censys_a

use recon/hosts-hosts/resolve   # resolve IP address

use recon/domains-hosts/shodan_hostname
add companies
use recon/domains-hosts/shodan_org

# site:ipinfo.io company.com  will get netblock info
recon/netblocks-hosts/shodan_net
recon/netblocks-ports/censysio  # enumerate ports info
~~~

- Import email list
~~~
use import/list
set FILENAME <filename>
set TABLE contacts
set COLUMN email
run
~~~


- Scrapping contact info
~~~
use recon/contacts-profiles/fullcontact
run
~~~


- More web stuff to use
~~~
use discovery/info_disclosure/interesting_files
run
~~~

- Export
~~~
use reporting/list
set TABLE <tbl_name>
set FILENAME <filename>
run
~~~

- Eyewitness
~~~
use reporting/list
set TABLE hosts
run

./EyeWitness.py -f <file_path> --web
~~~


##MIsc
- dirb stuff
~~~
use discovery/info_disclosure/interesting_files     # will generate traffic
run
~~~

- Stealth dirb
<https://hackertarget.com/extract-links/>








