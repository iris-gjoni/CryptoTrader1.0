import urllib
import json


class mo_money_mo_problems():	

	 def __init__(self):
	 	#setup any variables and call the classes
		public_key = raw_input("Enter wallet public key: ")	 	
		#self.pull_wallet_data(public_key)
		wallet_data = self.pull_wallet_data(public_key)
		print(wallet_data)		


	 def pull_wallet_data(self, wallet_address):
	 	#pull the data from the api
		etherscan = urllib.urlopen("https://api.etherscan.io/api?module=account&action=balance&address="+wallet_address+"&tag=latest&apikey=YourApiKeyToken").read()
		
		etherscan = json.loads(etherscan)		
		wallet = etherscan["result"]

		return wallet 	


	 def get_current_coin_price_in_usd(self, coin):
	 	return current_coin_price



	 def print_current_wallet_info(self):
	 	print("Starting_fiat: "+starting_fiat)
	 	print("Current fiat: "+current_fiat)
	 	print("Current ethereum: "+current_eth)


mmmp = mo_money_mo_problems()

