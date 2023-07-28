describe('Security Command Center', () => {
  beforeEach(() => {
    cy.visit(`/`);
    cy.shouldShowDashboardPage();
  });

  it('should show cctv videos after select a floor', () => {
    cy.selectFloor('Floor 1');
    cy.shouldShowCCTVsAndAlerts(4, 2);

    cy.openNthAlertPopover(0);
    cy.shouldShowAlertsListModal(2);
    cy.shouldShowButtonsForNthAlertItem(0);
    cy.shouldShowButtonsForNthAlertItem(1);
    // cy.clickSendAlertForNthAlertAndNthGuard(0, 0);
    // cy.clickIgnoreAlertForNthAlert(1);
    // cy.shouldShowCCTVsAndAlerts(4, 0);
  });
});
