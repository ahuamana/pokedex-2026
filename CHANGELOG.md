# Changelog

## [1.0.0] - 2026-02-07
### Added
- Initial release of the project.
- Module `:domain` to storage all the business logic of the application.
- Module `:data` to storage all the data access and manipulation logic of the application.
- Module `:presentation` to storage all the user interface and interaction logic of the application.
- Module `:utils` to storage all the utility functions and helpers for the application.
- Module `:testing` to storage all the mocks for the presentation layer.
- The screen `HomeScreen` to display the pokemon list in the application.
- The screen `DetailScreen` to display the pokemon details in the application.
- The screen `FavoriteScreen` to display the pokemon favorites in the application.
- The screen `DashboardScreen` to display the bottom navigation bar in the application.
- The widget `PokemonCard` to display the pokemon information in a card format in the application.
- Integrated root screens and navigation in the application.
- Implemented the use case `GetPokemonListUseCase` to fetch the pokemon list from the data layer.
- Integrated libraries for unit testing in the project.
- Integrated unit testing for mappers and data layer in the project.
- Covered 100% of the code with unit tests in the project in DATA layer and DOMAIN layer.

### Fixed
- Serialization issues in the data layer when fetching pokemon details with the library `json_serializable`.