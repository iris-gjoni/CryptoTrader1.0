import urllib
import json


class mo_money_mo_problems():	

	 def __init__(self):
	 	#setup any variables and call the classes
		public_key = raw_input("Enter wallet public key: ")
		wallet_data = self.pull_wallet_data(public_key)
		wallet_data = self.convert_wei_to_eth(wallet_data)
		self.print_current_wallet_info(wallet_data)		


	 def pull_wallet_data(self, wallet_address):
	 	#pull the data from the api
		etherscan = urllib.urlopen("https://api.etherscan.io/api?module=account&action=balance&address="+wallet_address+"&tag=latest&apikey=YourApiKeyToken").read()		
		etherscan = json.loads(etherscan)		
		wallet = etherscan["result"]
		return wallet

	 def convert_wei_to_eth(self, wei):
		try:
			eth = float(wei) / float(10**18)
		except:
			eth = 0
		return eth

	 def print_current_wallet_info(self, wallet):
	 	print("Ethereum balance: "+str(wallet))

         def get_current_coin_price_in_usd(self, coin):
                return current_coin_price



mmmp = mo_money_mo_problems()

