# Test Plan Documentation
Refer to RTM for the ID numbers
## ID = 1 
### Use case: Users should be able to open a User account with the Planetarium

#### 1. Requirement: Usernames and passwords should not be longer than 30 characters
| 0 character Username | 30 characters Username         | 31 characters Usernames         |
|----------------------|--------------------------------|---------------------------------|
|                      | KingofthePiratesoftheGrandLine | AKingofthePiratesoftheGrandLine |

| 0 character Password | 30 characters Password         |31 characters Password|
|----------------------|--------------------------------|-|
|                      | WholeCakeIslandWasAHorribleArc |WholeCakeIslandWasAHorribleArc!|

#### 2. Requirement: Users should have unique usernames
| Unique Username | Non-Unique Username |
|-----------------|---------------------|
| Luffy           | Zoro                |
For the non-unique username, we will have to ensure there is a created User with "Zoro" as their username

#### 3. Requirement: Passwords should never be visible in plaintext
- Password that should be obfuscated: "WholeCakeIslandWasAHorribleArc"
    - use for both Exploratory Testing and Error Guess Testing

#### Acceptance Testing Data
Use positive and negative test data found for the first two requirements 

## ID = 2
### Use case: Users should be able to securely access their account

#### 1. Requirement: Passwords should never be visible in plaintext
- Password that should be obfuscated: "WholeCakeIslandWasAHorribleArc"
    - use for both Exploratory Testing and Error Guess Testing

#### 2. Requirement: Only logged in Users should be able to access the Planetarium home page
- Use Error Guess Testing to check if a user can access the http://localhost:8080/planetarium without having logged in

## ID = 3
### Use case: Users should be able to see planets and moons added to the Planetarium

#### 1. Requirement: Only logged in Users should be able to access the Planetarium home page
- Use Error Guess Testing to check if a user can access the http://localhost:8080/planetarium without having logged in

#### Acceptance Testing 
Confirm upon login that there is data loaded in the User's planetarium

## ID = 4
### Use case: Users should be able to add new planets to the Planetarium

#### 1. Requirement: Planet names should not have more than 30 characters
| 0 character Planet | 30 characters Planet           | 31 characters Planet            |
|--------------------|--------------------------------|---------------------------------|
|                    | OdasWorldofOnePiecePirateStory | OdasWorldofOnePieceAPirateStory |

#### 2. Requirement: Planets should have unique usernames
| Unique Planet | Non-Unique Planet |
|---------------|-------------------|
| Raftel        | East Blue         |
For the non-unique planet name, we will have to ensure there is a created Planet with "East Blue" inside of a User's planetarium

#### 3. Requirement: Planets should be "owned" by the user that added it to the Planetarium
| Username | Password   |
|----------|------------|
| Luffy    | pirateking |
| Zoro     | firstmate  |

Their associated planets:

| User | Planet    |
|----------|-----------|
| Luffy    | Raftel    |
| Zoro     | East Blue |

Use acceptance testing to confirm if you can see Raftel in Zoro's planetarium and East Blue in Luffy's planetarium

#### 4. Requirement: Planets should allow adding an associated image, but an image should not be required for the data to be added to the database
| Planet    | Image      |
|-----------|------------|
| Loguetown | pirateking |
| Zoro      | firstmate  |


#### Acceptance Testing Data
Use positive and negative test data found for the first two and the fourth requirements 


