# TODO for Selective Reassignment Feature in Admin Dashboard

## Completed
- Updated admin-dashboard.html to add "Select Members" column with UI for selective reassignment of group tasks.
- Updated AdminController.java to add endpoints for:
  - Reassigning group task to all members
  - Reassigning group task to selected members
- Updated TaskService.java to implement business logic for:
  - Reassigning group task to all members
  - Reassigning group task to selected members with validation and notifications

## Next Steps
- Test the selective reassignment feature end-to-end:
  - Verify UI displays correctly in admin dashboard
  - Verify reassign to all members works as expected
  - Verify reassign to selected members works as expected
  - Verify notifications are sent correctly
- Add any necessary unit/integration tests for new controller and service methods
- Review and refactor code if needed based on testing feedback

## Notes
- Ensure CSRF tokens are handled correctly in forms
- Confirm UI usability for member selection dropdown
