# Academic Management System
A comprehensive system for managing academic activities, study tips, and generating reports. Students can track their extracurricular activities, earn credits, share study resources, and monitor their academic progress.

## Features
### Activity Management
- **Types**: Monitoria (Tutoring), Estágio (Internship), Extensão (Extension Projects), Representação Estudantil (Student Representation)
- **Validation**: Ensures valid discipline names, company names, and activity durations
- **Credit Calculation**:
  - Monitoria: 4 credits/semester (max 16)
  - Estágio: 1 credit/60 hours (max 18)
  - Extensão: 10 credits/12 months (max 18)
  - Representação: 2 credits/year (max 2)

### Study Tips System
- **Multimedia Support**: Add videos/audios with duration tracking
- **Reference Management**: Verified academic references with importance ratings
- **Bonus System**: Earn points for valuable contributions
  - Text: Up to 50 points for detailed explanations
  - Media: 5 points/minute (max 50)
  - Verified References: 15 points each

### Reporting
- **Progress Tracking**: Real-time credit accumulation monitoring
- **Report Types**:
  - Final Report: Overall credit summary
  - Activity-Specific: Detailed breakdown per activity type
  - Historical Tracking: Save/retrieve previous reports

### User Management
- Secure authentication
- Password policies (exactly 8 characters)
- Bonus ranking system
- Student comparison by name/alphabetical order

## Technologies Used

- **Core**: Java 21+
- **Testing**: JUnit 5
- **Validation**: Custom middleware for null/empty checks
- **Design Pattern**: Facade for simplified API

## Project Structure

```
.
├── main/java/
│   ├── atividade/          # Activity classes and controller
│   ├── dica/               # Study tips system
│   ├── relatorio/          # Report generation
│   ├── usuario/            # User management
│   ├── middleware/         # Validation utilities
│   └── facade/             # Unified system interface
├── test/java/              # Comprehensive unit tests
└── README.md
```

## Installation & Usage

1. **Compile**:
```bash
mvn compile
```

2. **Run Tests**:
```bash
mvn test
```

3. **Sample Usage**:
```java
FacadeComplementaACAO system = new FacadeComplementaACAO();

// Create student
system.criarEstudante("Maria Silva", "123.456.789-09", "password", "124110000");

// Add internship activity
String codigo = system.criarAtividadeEstagioEmEstudante(
    "123.456.789-09", "password", 300, "Google"
);

// Generate report
String relatorio = system.gerarRelatorioFinal(
    "123.456.789-09", "password"
);
```

## What I Learned
1. **Validation Patterns**:
   - Null/empty checks with custom exceptions
   - CPF format validation
   - Password complexity requirements

2. **Testing Strategies**:
   - Parameterized JUnit tests
   - Edge case testing (empty strings, negative values)
   - Test coverage for 100% of validation logic

3. **System Design**:
   - Facade pattern for simplified access
   - Polymorphic activity handling
   - Credit calculation strategies

4. **Data Management**:
   - HashMaps for user storage
   - ArrayLists for activity tracking
   - LinkedHashMap for ordered credit limits

5. **Reporting**:
   - Dynamic report generation
   - Historical data persistence
   - Progress tracking algorithms

## Contributors
- [NotAdson] - Sole developer
