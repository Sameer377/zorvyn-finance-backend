API Endpoints

Admin creds 
admin@gmail.com
admin@123

Base URL
http://localhost:9000

All endpoints are secured using JWT unless mentioned otherwise.

Authentication
Login

POST /api/v1/auth/login
Authenticate a user and receive a JWT token.

Users
Get user by ID

GET /api/v1/users/{id}

Create user

POST /api/v1/users

Update user

PUT /api/v1/users/{id}

Delete user

DELETE /api/v1/users/{id}

Get users with filters

GET /api/v1/users
Supports pagination and filtering.

Update user status

PATCH /api/v1/users/{id}/status

Change user role

PATCH /api/v1/users/{id}/role

Transactions
Get transaction by ID

GET /api/v1/transactions/{id}

Create transaction

POST /api/v1/transactions

Update transaction

PUT /api/v1/transactions/{id}

Delete transaction

DELETE /api/v1/transactions/{id}

Get transactions with filters

GET /api/v1/transactions

Supports filtering by:

userId
type
status
category
account numbers
date range
pagination
Categories
Get category by ID

GET /api/v1/categories/{id}

Create category

POST /api/v1/categories

Update category

PUT /api/v1/categories/{id}

Delete category

DELETE /api/v1/categories/{id}

Get all categories

GET /api/v1/categories

Dashboard
Get total income

GET /api/v1/dashboard/total-income

Get total expense

GET /api/v1/dashboard/total-expense

Get net balance

GET /api/v1/dashboard/net-balance

Get category-wise summary

GET /api/v1/dashboard/category-summary

Get monthly summary

GET /api/v1/dashboard/monthly

Get recent transactions

GET /api/v1/dashboard/recent