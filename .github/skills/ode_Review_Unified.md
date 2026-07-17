
# SKILL: Code_Review_Unified

## Metadata
- **VERSION**: 1.0
- **PURPOSE**: Comprehensive code review with team-specific checklist validation
- **TOKEN_BUDGET**: ~500 tokens (input + output)
- **OUTPUT_FORMAT**: JSON (structured, parseable)

## Context References
- ORG_CODING_STANDARDS.md (Organization-wide coding standards)
- CODE_REVIEW_CHECKLIST.md (Team-specific checklist)

## Parameters
Replace these before using:
- `REVIEW_MODE`: [STANDARD | SECURITY_FOCUSED | PERFORMANCE_FOCUSED | QUICK]

## Task
Review pull request code for quality, security, correctness, and compliance with team standards.

## Input
```
[PR Code/Content]
[PR Description]
[Affected Modules]
```

## Review Criteria

### 1. CODE QUALITY
- Follows ORG_CODING_STANDARDS.md (naming, complexity, length)
- Functions/methods <= 20 lines
- Cyclomatic complexity <= 3
- Clear variable and function names
- No magic numbers or hardcoded values
- Proper error handling with logging

### 2. TESTING
- Unit tests added (coverage >= 70% for new code)
- Edge cases identified and tested (>=3 per function)
- Test naming convention: [method]_[scenario]_[expected]
- Mocks/stubs used correctly to isolate dependencies
- Error cases and boundary values tested

### 3. SECURITY
- No hardcoded secrets (passwords, API keys, tokens)
- SQL injection prevention (parameterized queries)
- Input validation present
- Authentication/authorization checks (if applicable)
- No security vulnerabilities in dependencies
- Data privacy considerations (GDPR, PII handling)

### 4. CORRECTNESS
- Code implements PR description accurately
- No breaking changes to existing APIs
- Backward compatibility maintained
- All referenced issues/requirements addressed

### 5. DOCUMENTATION
- Complex logic has comments explaining WHY (not WHAT)
- API changes documented
- New functions have descriptions
- Deprecated code has migration path

### 6. PERFORMANCE
- No obvious performance regressions
- Large data handling optimized (loops, queries)
- Memory usage acceptable
- Database queries optimized (indexes considered)

## Team-Specific Checklist
Reference: TEAM_CHECKLISTS/[TEAM_NAME].md
The skill will validate against team-specific items during review.

## Self-Validation Checklist
Before responding, verify:
- ☑ Code matches PR description
- ☑ Unit tests added (coverage >= 80%)
- ☑ Edge cases tested (>=3)
- ☑ No security vulnerabilities
- ☑ No hardcoded values
- ☑ Performance acceptable
- ☑ Code standards met
- ☑ Error handling present
- ☑ Backward compatible
- ☑ Documentation updated
- ☑ Team-specific checklist passed

## Output Format (JSON)
```json
{
  "review_metadata": {
    "pr_id": "[PR number]",
    "pr_title": "[Title]",
    "team": "[Team Name]",
    "review_mode": "[STANDARD|SECURITY_FOCUSED|...]",
    "timestamp": "[ISO 8601 date]"
  },
  "review_status": {
    "overall_status": "APPROVED | CHANGES_REQUIRED | COMMENTS_ONLY",
    "critical_issues_count": 0,
    "high_issues_count": 0,
    "medium_issues_count": 0,
    "low_issues_count": 0
  },
  "critical_issues": [
    {
      "id": "CRIT_001",
      "category": "Security | Correctness | Testing | Performance",
      "severity": "CRITICAL",
      "title": "[Issue title]",
      "description": "[What's wrong and why it matters]",
      "code_location": "[File:Line or Function Name]",
      "code_example": "[Before code snippet]",
      "fix_suggestion": "[How to fix]",
      "fix_example": "[After code snippet]",
      "impact": "[High/Medium/Low impact on production]"
    }
  ],
  "issues": [
    {
      "id": "HIGH_001",
      "category": "[Code Quality | Testing | Documentation | etc]",
      "severity": "HIGH | MEDIUM | LOW",
      "title": "[Issue title]",
      "description": "[What to improve]",
      "code_location": "[Where in code]",
      "suggestion": "[How to improve]"
    }
  ],
  "checklist_results": {
    "code_quality": {
      "passed": true,
      "items": [
        { "check": "Follows naming standards", "result": "PASS" },
        { "check": "Function length <= 20 lines", "result": "PASS" },
        { "check": "Complexity <= 3", "result": "PASS" }
      ]
    },
    "testing": {
      "passed": true,
      "items": [
        { "check": "Unit tests added", "result": "PASS", "coverage": "75%" },
        { "check": "Edge cases tested (>=3)", "result": "PASS", "count": 5 },
        { "check": "Test naming convention", "result": "PASS" }
      ]
    },
    "security": {
      "passed": true,
      "items": [
        { "check": "No hardcoded secrets", "result": "PASS" },
        { "check": "SQL injection prevention", "result": "PASS" },
        { "check": "Input validation", "result": "PASS" }
      ]
    },
    "correctness": {
      "passed": true,
      "items": [
        { "check": "Matches PR description", "result": "PASS" },
        { "check": "No breaking changes", "result": "PASS" },
        { "check": "Backward compatible", "result": "PASS" }
      ]
    },
    "team_specific": {
      "passed": true,
      "team": "[Team Name]",
      "items": [
        { "check": "[Team-specific check 1]", "result": "PASS" },
        { "check": "[Team-specific check 2]", "result": "PASS" }
      ]
    }
  },
  "positives": [
    "[What was done well - positive feedback item]",
    "[Another positive aspect]"
  ],
  "approval_decision": {
    "status": "APPROVED | CHANGES_REQUIRED | COMMENTS_ONLY",
    "summary": "[Brief summary for PR author]",
    "next_steps": "[What happens next]",
    "required_changes": "[List of changes needed before approval (if CHANGES_REQUIRED)]"
  },
  "metrics": {
    "input_tokens": "[Approximate]",
    "output_tokens": "[Approximate]",
    "total_tokens": "[For tracking efficiency]",
    "review_time_estimate": "[In minutes]"
  }
}
```

## Usage Examples

### Example 1: Standard Review (Modernization Team)
```
SKILL: Code_Review_Unified
TEAM: Modernization
REVIEW_MODE: STANDARD

[Paste PR code here]
[PR Description]
[Affected modules]
```

### Example 2: Security-Focused (ETL Team)
```
SKILL: Code_Review_Unified
TEAM: ETL
REVIEW_MODE: SECURITY_FOCUSED

[Paste PR code here]
```

### Example 3: Quick Review (SQL Migration Team)
```
SKILL: Code_Review_Unified
TEAM: SQL_Migration
REVIEW_MODE: QUICK

[Paste PR code here]
```

## Review Modes Explained

### STANDARD (Default)
- All criteria checked (code quality, testing, security, docs, perf)
- Time: 5-10 minutes
- Best for: Feature PRs, refactoring

### SECURITY_FOCUSED
- Only: Security, authentication, data protection
- Skip: Style, minor improvements, docs
- Time: 3-5 minutes
- Best for: PRs touching auth, payments, sensitive data

### PERFORMANCE_FOCUSED
- Only: Performance, scalability, resource usage
- Skip: Style, documentation
- Time: 5-7 minutes
- Best for: PRs affecting APIs, databases, critical paths

### QUICK
- Only: Critical issues (logic, security, major bugs)
- Skip: Style, minor improvements
- Time: 2-3 minutes
- Best for: Hotfixes, urgent patches

## Tips for Best Results

1. **Include context**: Provide PR description and affected modules
2. **Clear code**: Ensure code is readable in the prompt
3. **Team checklist**: Reference correct team checklist file
4. **Review mode**: Choose appropriate mode for PR type
5. **Output use**: JSON format is designed for easy parsing and sharing

## Version History
- v1.0: Initial release with JSON output, org-specific standards