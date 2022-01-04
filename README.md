# PromotionEngine
implemented a simple promotion engine for a checkout process. Cart contains a list of single character SKU ids (A, B, C.	) over which the promotion engine will need to run.
The promotion engine will need to calculate the total order value after applying the 2 promotion types

* buy 'n' items of a SKU for a fixed price (3 A's for 130)
* buy SKU 1 & SKU 2 for a fixed price ( C + D = 30 )

The promotion engine should be modular to allow for more promotion types to be added at a later date (e.g. a future promotion could be x% of a SKU unit price). For this coding exercise you can assume that the promotions will be mutually exclusive; in other words if one is applied the other promotions will not apply

### Test Setup
Unit price for SKU IDs <br />
|   A - 50   |    B  - 30    |    C - 20    |    D - 15   | 

#### Active Promotions<br />
3 of A's for 130<br />
2 of B's for 45 <br />
C & D for 30<br />

#### Scenario A<br />
 1 * A  = 50 <br />
 1 * B = 30 <br />
 1 * C = 20 <br />
Total	= 100<br />

#### Scenario B	<br />
5 * A	= 130 + 2*50 <br />
5 * B	=	45 + 45 + 30<br />
1 * C	=	20<br />
Total	= 370<br />

#### Scenario C<br />
3	* A	 = 130 <br />
5	* B	 = 45 + 45 + 1 * 30<br />
1	* C	 = - <br />
1	* D	 = 30 <br />
Total	 = 280 <br />
# Solution
Console application using Java 11 and TDD approach followed using Junit4
### Modules
Application is segregated into 4 modules
* Cart
* Checkout
* Promotion
* Engine
* Sku
#### Promotion
This module has Promotion factory class to return desired promotion object. PromotionService class caters methhod to checkout modules and test cases.
There are two implemented promotion type class and one is non-implemented and can be used later to add other type of implementation
* PromotionBulkItem
* PromotionCombination
* PromotionPercentage <br />
````
    private PromoType promoType; // PromoType is a enum and on the basic of this factory method return instance
    private int quantity; // total quantity applicable for promotion, this can be used in future as N in Buy_N_Get_M free or will be 1 for percentage
    private int promoPrice; // flat price for quantity/combination in promotion, this can be used in future as M in Buy_N_Get_M free or will be percent for percentage type promo
    private char[] unitsInCombination; /// this variable is used for combination type of promo example new char ['A','C']
````
In future if we want to add one more type of promotion then Promotion<new_type> class can be created which implements promotion
and Factory class can return on the basis of PromoType Enum.
#### Sku
Sku module contains modal for sku which has 2 variable and getters, setters, toString,equals, hashcode functions are overriden.
````
private char skuId;
private int unitPrice;
````
#### Cart
This module has 2 modal classes
* Cart extends HashSet<CartItem>
* CartItem <br />
````
    private char sku;
    private int quantity;
    private int unitPrice;
    private int totalPrice;
`````
CartItem class has 4 variables and getters, setters, toString, equals, hashcode functions are overriden.
This module has service class

* cartServiceImpl implements CartService <br />

cartServices offers methods as

* addToCart(Sku sku, Integer quantity)
* getMyCart()
#### Checkout
Checkout module has checkout services which is an entry point to apply promotion from front unit in this case MainApplication class. CheckoutServiceImpl a methods i.e
* checkoutCartWithPromo(Cart cart, Map<Character, Promotion> activePromotionMap)

checkoutCartWithPromo takes cart and active promotion instance and apply those promotions if they are mapped to sku in cart. This method return total amount of cart with applied  promotions.
#### engine and main applicaton
For this solution engine unit has engineUtility class which caters to MainApplication. engineUtilityImpl has below methods
* void setup() 
* boolean addSku(Sku sku)
* Map<Character, Sku> getSkuList()
* boolean mapSkuToPromotion(char sku, Promotion promotion)
* Map<Character, Promotion> getActivePromotionMap()
* Promotion createPromotion(PromoType promoType, int quantity, int unitPrice, char[] unitsInCombination);

Refactoring and improvements can be done with above methods and class, but these methods are currently used to stub promotion and sku data for MainApplication and test cases<br/>
### Promotion mapping - mapSkuToPromotion 
mapSkuToPromotion method is used to map promotion to sku. Currently this mapping is one-to-one means one sku will always mapped to oe promotion.<br/>
To maintain mutual exclusion, mapping is one-to-one and if we try to add other promotion it wont get overriden in map.<br/>
To replace a mapped promotion, remove current promotion from map and add other promotion
_____________________________________
### PromotionEngine Test
Test classes are also divided in to pakage as per modules to test each functions and scenarios.
* #### cart -> cartServiceImplTest <br/>
  * addToCart_whenAddedThreeSkuOfAToCart_thenCartHasOneItemWithThreeA - 3As aded to cart once 
  * addToCart_whenAddedThreeSkuOfAandTwoSkuOfAagainToCart_thenCartHasOneItemWithFiveA - 3As addded and 2As added again to cart
* #### checkout -> CheckoutServiceImplTest <br/>
  * Scenario A - whenOneAOneBOneCExistsTotalAfterPromotionIs100  - 1A 1B 1C at 100
  * Scenario B - whenFiveAFiveBOneCExistsTotalAfterPromotionIs370 - 5A 5B 1C at 370
  * Scenario C - whenThreeAFiveBOneCOneDExistsTotalAfterPromotionIs280 - 3A 5B 1C 1D at 280
  * Scenario D - testEmptyCart 
* #### engine -> EngineUtilityTest<br/>
  * mapSkuToPromotion_whenPromoMapsOnceToSKU_returnsTrue - map one promo to sku A
  * mapSkuToPromotion_whenPromoMaps2ndTimeToSKU_returnsFalse - map 2nd promo when first promo is already maps to A
  * mapSkuToPromotion_whenPromoMaps2ndTimeAfterRemovingExistingPromoToSKU_returnsTrue - map 2nd promo after removing first promo to A
  * setup_whenSetup_thenSkuListAndActivePromotionMapGetsStubbed - test if skuList and activePromotionMap is getting Stubbed for mainApplication
* #### promotion -> PromotionEngineTest <br/>
  * whenThreeAExistsTotalAfterPromotionIs130 - 3A at 130
  * whenTwoBExistsTotalAfterPromotionIs45 - 2B at 45
  * whenCAndDExistsCombinedTotalAfterPromotionIs30 - C+D at 30
  * whenNoPromotionExists - should calculate normal item cost without promo
### Execution
Add project from maven source in IDE
Can be run via intellij IDE but addding run configuration as Application
````bash
 mvn test
 mvn install
 java -classpath "<project path>\PromotionEngine\target\classes" com.company.engine.MainApplication
````
    
