import { browser, protractor } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { GodownStockComponentsPage, GodownStockUpdatePage } from './godown-stock.page-object';

describe('GodownStock e2e test', () => {
    let navBarPage: NavBarPage;
    let godownStockUpdatePage: GodownStockUpdatePage;
    let godownStockComponentsPage: GodownStockComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load GodownStocks', () => {
        navBarPage.goToEntity('godown-stock');
        godownStockComponentsPage = new GodownStockComponentsPage();
        expect(godownStockComponentsPage.getTitle()).toMatch(/projectGhApp.godownStock.home.title/);
    });

    it('should load create GodownStock page', () => {
        godownStockComponentsPage.clickOnCreateButton();
        godownStockUpdatePage = new GodownStockUpdatePage();
        expect(godownStockUpdatePage.getPageTitle()).toMatch(/projectGhApp.godownStock.home.createOrEditLabel/);
        godownStockUpdatePage.cancel();
    });

    it('should create and save GodownStocks', () => {
        godownStockComponentsPage.clickOnCreateButton();
        godownStockUpdatePage.setCurrentQuantityInput('5');
        expect(godownStockUpdatePage.getCurrentQuantityInput()).toMatch('5');
        godownStockUpdatePage.setAddedQuantityInput('5');
        expect(godownStockUpdatePage.getAddedQuantityInput()).toMatch('5');
        godownStockUpdatePage.setConsumedQuantityInput('5');
        expect(godownStockUpdatePage.getConsumedQuantityInput()).toMatch('5');
        godownStockUpdatePage.setDescriptionInput('description');
        expect(godownStockUpdatePage.getDescriptionInput()).toMatch('description');
        godownStockUpdatePage.setStatusInput('5');
        expect(godownStockUpdatePage.getStatusInput()).toMatch('5');
        godownStockUpdatePage.setCreatedByInput('5');
        expect(godownStockUpdatePage.getCreatedByInput()).toMatch('5');
        godownStockUpdatePage.setModifiedByInput('5');
        expect(godownStockUpdatePage.getModifiedByInput()).toMatch('5');
        godownStockUpdatePage.setCreatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(godownStockUpdatePage.getCreatedAtInput()).toContain('2001-01-01T02:30');
        godownStockUpdatePage.setUpdatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(godownStockUpdatePage.getUpdatedAtInput()).toContain('2001-01-01T02:30');
        godownStockUpdatePage.pickListVarietySelectLastOption();
        godownStockUpdatePage.pickListCategorySelectLastOption();
        godownStockUpdatePage.pickListQuantityTypeSelectLastOption();
        godownStockUpdatePage.godownSelectLastOption();
        godownStockUpdatePage.save();
        expect(godownStockUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
