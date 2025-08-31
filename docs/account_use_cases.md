# 📘 Use Case Documentation (DDD Style)

------------------------------------------------------------------------

### 1. **ChangeAccountName**

-   **Command:** `ChangeAccountName`
    -   Parameters: `accountId: UUID`, `newName: String`
-   **Aggregate:** `Account`
-   **Behavior:**\
    Updates the display name of the account by replacing the
    `AccountName` value.\
-   **Result:**
    -   Success → `Account` with updated name\
    -   Failure → `IllegalArgumentException` if the new name is invalid

------------------------------------------------------------------------

### 2. **ChangeAccountPassword**

-   **Command:** `ChangeAccountPassword`
    -   Parameters: `accountId: UUID`, `oldPassword: String`,
        `newPassword: String`
-   **Aggregate:** `Account`
-   **Behavior:**\
    Verifies the old password and replaces it with a new one through
    `AccountPassword`.\
-   **Result:**
    -   Success → `Account` with updated password\
    -   Failure → `IllegalArgumentException` if the old password does
        not match

------------------------------------------------------------------------

### 3. **CreateAccount**

-   **Command:** `CreateAccount`
    -   Parameters: `username: String`, `password: String`,
        `name: String`
-   **Aggregate:** `Account`
-   **Behavior:**\
    Creates a new account with a unique `AccountId` and initializes
    `AccountUsername`, `AccountPassword`, and `AccountName`.\
-   **Result:**\
    A new `Account` object (with empty friends and subscribers lists)

------------------------------------------------------------------------

### 4. **CreateAdditionalInformation**

-   **Command:** `CreateAdditionalInformation`
    -   Parameters: `accountId: UUID`, `info: AdditionalInformation`
-   **Aggregate:** `Account`
-   **Behavior:**\
    Adds or updates `AdditionalInformation` for the given account.\
-   **Result:**\
    Updated `Account` with filled `additionalInformation`

------------------------------------------------------------------------

### 5. **RetrieveUser**

-   **Command:** `RetrieveUser`
    -   Parameters: `accountId: UUID` or `username: String`
-   **Aggregate:** `Account`
-   **Behavior:**\
    Retrieves a domain object `Account` from the repository by ID or
    username.\
-   **Result:**
    -   Success → `AccountResponse` with user details\
    -   Failure → Exception if the user is not found

------------------------------------------------------------------------

### 6. **SubscribeOnUser**

-   **Command:** `SubscribeOnUser`
    -   Parameters: `subscriberId: UUID`, `targetUserId: UUID`
-   **Aggregate:** `Account`
-   **Behavior:**
    -   If subscriber and target are not yet connected → adds the
        subscriber to the target's subscribers list.\
    -   If they are already friends → throws an error.\
    -   If subscription becomes mutual → moves the relation into the
        friends list.\
-   **Result:**
    -   Success → updated `friends`/`subscribers` lists for both
        accounts\
    -   Failure → `IllegalArgumentException` if rules are violated
