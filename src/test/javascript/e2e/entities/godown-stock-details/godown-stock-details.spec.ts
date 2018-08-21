import { browser, protractor } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { GodownStockDetailsComponentsPage, GodownStockDetailsUpdatePage } from './godown-stock-details.page-object';

describe('GodownStockDetails e2e test', () => {
    let navBarPage: NavBarPage;
    let godownStockDetailsUpdatePage: GodownStockDetailsUpdatePage;
    let godownStockDetailsComponentsPage: GodownStockDetailsComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load GodownStockDetails', () => {
        navBarPage.goToEntity('godown-stock-details');
        godownStockDetailsComponentsPage = new GodownStockDetailsComponentsPage();
        expect(godownStockDetailsComponentsPage.getTitle()).toMatch(/projectGhApp.godownStockDetails.home.title/);
    });

    it('should load create GodownStockDetails page', () => {
        godownStockDetailsComponentsPage.clickOnCreateButton();
        godownStockDetailsUpdatePage = new GodownStockDetailsUpdatePage();
        expect(godownStockDetailsUpdatePage.getPageTitle()).toMatch(/projectGhApp.godownStockDetails.home.createOrEditLabel/);
        godownStockDetailsUpdatePage.cancel();
    });

    it('should create and save GodownStockDetails', () => {
        godownStockDetailsComponentsPage.clickOnCreateButton();
        godownStockDetailsUpdatePage.setDateInput('2000-12-31');
        expect(godownStockDetailsUpdatePage.getDateInput()).toMatch('2000-12-31');
        godownStockDetailsUpdatePage.setQuantityInput('5');
        expect(godownStockDetailsUpdatePage.getQuantityInput()).toMatch('5');
        godownStockDetailsUpdatePage.setDescriptionInput('description');
        expect(godownStockDetailsUpdatePage.getDescriptionInput()).toMatch('description');
        godownStockDetailsUpdatePage.setStatusInput('5');
        expect(godownStockDetailsUpdatePage.getStatusInput()).toMatch('5');
        godownStockDetailsUpdatePage.setCreatedByInput('5');
        expect(godownStockDetailsUpdatePage.getCreatedByInput()).toMatch('5');
        godownStockDetailsUpdatePage.setModifiedByInput('5');
        expect(godownStockDetailsUpdatePage.getModifiedByInput()).toMatch('5');
        godownStockDetailsUpdatePage.setCreatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(godownStockDetailsUpdatePage.getCreatedAtInput()).toContain('2001-01-01T02:30');
        godownStockDetailsUpdatePage.setUpdatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(godownStockDetailsUpdatePage.getUpdatedAtInput()).toContain('2001-01-01T02:30');
        godownStockDetailsUpdatePage.godownStockSelectLastOption();
        godownStockDetailsUpdatePage.save();
        expect(godownStockDetailsUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
