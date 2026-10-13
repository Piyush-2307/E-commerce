# E-Commerce Backend

A RESTful e-commerce backend built with **Java, Spring Boot, Spring Data JPA, and PostgreSQL**. The application supports user and product management, shopping carts, order placement, inventory management, and GST-inclusive pricing.

## 1. Features

- User registration, retrieval, updating, and deletion.
- Product CRUD operations with pagination.
- Shopping cart creation, item addition, removal, and summary generation.
- Order placement from a user's cart.
- Inventory validation and stock deduction during checkout.
- Order retrieval and cancellation.
- Automatic 18% GST calculation using `BigDecimal`.
- DTOs and mappers for request handling and response generation.
- Transactional order placement using Spring's `@Transactional`.

## 2. Architecture

The application follows a layered architecture:

- **Controller:** Exposes REST API endpoints and handles HTTP requests.
- **Service:** Implements business logic and coordinates operations.
- **Repository:** Handles database operations through Spring Data JPA.
- **Entity:** Represents database tables and their relationships.
- **DTO:** Defines request and response data structures.
- **Mapper:** Converts between entities and DTOs.
- **Orchestrator:** Coordinates the checkout workflow across services.

### Order placement workflow

1. Retrieve the user's cart and validate that it contains items.
2. Check product availability and requested quantities.
3. Create an order and its associated order items.
4. Calculate GST-inclusive prices and the total order amount.
5. Deduct purchased quantities from product inventory.
6. Clear the cart after successful order creation.

Order placement runs inside a transaction so that database changes can roll back if an operation fails.

## 3. Database Design

| Entity | Purpose |
|---|---|
| `User` | Stores user account information. |
| `Address` | Stores a user's address. |
| `Product` | Stores product details, prices, and stock quantities. |
| `Cart` | Represents a user's shopping cart. |
| `CartItem` | Stores products and quantities in a cart. |
| `Order` | Stores order status, total amount, and creation time. |
| `OrderItem` | Stores purchased products, quantities, and prices at purchase. |

### Relationships

- User ↔ Address: One-to-one.
- User → Cart: One-to-one.
- User → Orders: One-to-many.
- Cart → CartItems: One-to-many.
- Product → CartItems: One-to-many.
- Order → OrderItems: One-to-many.
- Product → OrderItems: One-to-many.

`OrderItem` stores the purchase price separately from the current product price, preserving the price used when the order was placed.

## 4. API Endpoints

### Users — `/api/users`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/create` | Create a user |
| GET | `/{id}` | Retrieve a user |
| GET | `/` | Retrieve users with pagination |
| PUT | `/update/{id}` | Update a user |
| DELETE | `/delete/{id}` | Delete a user |

### Products — `/api/products`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/` | Create a product |
| GET | `/{id}` | Retrieve a product |
| GET | `/` | Retrieve products with pagination |
| PUT | `/{id}` | Update a product |
| DELETE | `/{id}` | Delete a product |

### Cart — `/api/carts`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/users/{id}/item` | Add an item to a user's cart |
| GET | `/users/{id}/cart` | Retrieve a user's cart |
| DELETE | `/{cartId}/item/{productId}` | Remove a cart item |
| GET | `/{id}/summary` | Retrieve cart totals and item details |

### Orders — `/api/orders`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/{userId}` | Place an order using the user's cart |
| GET | `/{orderId}` | Retrieve an order |
| PATCH | `/{orderId}` | Cancel an order |

## 5. Pricing and Inventory

The `PricingService` applies 18% GST to product prices and calculates line totals using `BigDecimal` to reduce floating-point precision issues.

During checkout, the application validates available stock, deducts purchased quantities, and stores the purchase price in each order item.

## 6. Technology Stack

- Java
- Spring Boot
- Spring Data JPA / Hibernate
- PostgreSQL
- Jakarta Validation
- Maven
- Lombok

## 7. Running the Project

1. Install Java and Docker.
2. Clone the repository.
3. Configure PostgreSQL connection details in `application.properties` or `application.yml`.
4. Start PostgreSQL using the project's Docker Compose configuration, if provided.
5. Run the application using Maven:

   ```bash
   ./mvnw spring-boot:run
   ```

6. Test the REST endpoints using Postman or another API client.

Ensure the database credentials and required environment variables are configured before starting the application.

## 8. Future Improvements

- Centralized exception handling and meaningful HTTP status codes.
- Password hashing and authentication/authorization.
- Safer inventory updates under concurrent checkout requests.
- Inventory restoration and cancellation-state validation.
- Consistent REST endpoint naming.
- Automated unit and integration tests.


