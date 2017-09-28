import urllib
import json

class mo_money_mo_problems():

	 all_coin_info = []
	 user_wallet = {}
	 eth_to_usd = 0.0

	 def __init__(self):
	 	#setup any variables and call the classes
		self.pull_all_coin_info()
		
		self.run()


	 def run(self):
		print("1) Login\n2) Check wallet balance\n3) Add coin to wallet\n4) Quit")
		while (int(user_input) < 0 and int(user_input) > 4):
			user_input = self.get_user_input("Option: ")
		switch(user_input) = {
			case 0:
				print("logging in ")
			case 1:
				self.check_wallet_balance()
			case 3:
				print("add coin to wallet")
			default:
				print("Bye bye")
				break
			};

	 def check_wallet_balance(self):
		eth_price = self.get_current_coin_price_in_usd("eth")
		public_key = get_user_input("Enter wallet public key: ")
		wallet_data = self.pull_wallet_data(public_key)
		if (wallet_data == False):
                        print("Wallet not found")
                wallet_data = self.convert_wei_to_eth(wallet_data)
                usd_value = self.calculate_worth_usd(wallet_data, eth_price)
                self.print_current_wallet_info(wallet_data,usd_value)

		
	
	 def get_user_input(self, message):
		user_input = raw_input(message)	
		return user_input


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

	 def pull_all_coin_info(self):
		global all_coin_info
                coinscan= urllib.urlopen("https://api.coinmarketcap.com/v1/ticker/?limit=10").read()
                all_coin_info = json.loads(coinscan)

         def get_current_coin_price_in_usd(self, coin):
		global all_coin_info
		for coin_info in all_coin_info:
			if (coin_info["id"].lower == coin):
				eth_price = eth_info["price_usd"]
				return eth_price
		return None

mmmp = mo_money_mo_problems()
