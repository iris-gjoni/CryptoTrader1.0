import urllib
import json

class mo_money_mo_problems():	

	 def __init__(self):
	 	#setup any variables and call the classes
		eth_price = self.get_current_coin_price_in_usd("eth")
		public_key = raw_input("Enter wallet public key: ")
		wallet_data = self.pull_wallet_data(public_key)
		if (wallet_data == False): 
			print("no thank you")
		wallet_data = self.convert_wei_to_eth(wallet_data)
		usd_value = self.calculate_worth_usd(wallet_data, eth_price)
		self.print_current_wallet_info(wallet_data,usd_value)

	 def pull_wallet_data(self, wallet_address):
	 	#pull the data from the api
		etherscan = urllib.urlopen("https://api.etherscan.io/api?module=account&action=balance&address="+wallet_address+"&tag=latest&apikey=YourApiKeyToken").read()		
		etherscan = json.loads(etherscan)
		status = etherscan["status"]
		if (status == 0): 
			return False 
		wallet = etherscan["result"]
		return wallet

	 def convert_wei_to_eth(self, wei):
		try:
			eth = float(wei) / float(10**18)
		except:
			eth = 0
		return eth

	 def print_current_wallet_info(self, eth_balance, usd_balance):
	 	print("Ethereum balance: "+str(eth_balance)+" worth: $"+str(usd_balance))

	 def calculate_worth_usd(self, total_eth,current_price):
		price = float(total_eth) * float(current_price)
		price = "{0:.2f}".format(price)
		return price

         def get_current_coin_price_in_usd(self, coin):
                coinscan= urllib.urlopen("https://api.coinmarketcap.com/v1/ticker/?limit=10").read()
		coinscan = json.loads(coinscan)
		eth_info = coinscan[1]
		eth_price = eth_info["price_usd"]		
		return eth_price

mmmp = mo_money_mo_problems()
