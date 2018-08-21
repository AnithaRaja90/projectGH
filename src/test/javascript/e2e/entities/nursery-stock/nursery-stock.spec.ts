import { browser, protractor } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { NurseryStockComponentsPage, NurseryStockUpdatePage } from './nursery-stock.page-object';

describe('NurseryStock e2e test', () => {
    let navBarPage: NavBarPage;
    let nurseryStockUpdatePage: NurseryStockUpdatePage;
    let nurseryStockComponentsPage: NurseryStockComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load NurseryStocks', () => {
        navBarPage.goToEntity('nursery-stock');
        nurseryStockComponentsPage = new NurseryStockComponentsPage();
        expect(nurseryStockComponentsPage.getTitle()).toMatch(/projectGhApp.nurseryStock.home.title/);
    });

    it('should load create NurseryStock page', () => {
        nurseryStockComponentsPage.clickOnCreateButton();
        nurseryStockUpdatePage = new NurseryStockUpdatePage();
        expect(nurseryStockUpdatePage.getPageTitle()).toMatch(/projectGhApp.nurseryStock.home.createOrEditLabel/);
        nurseryStockUpdatePage.cancel();
    });

    it('should create and save NurseryStocks', () => {
        nurseryStockComponentsPage.clickOnCreateButton();
        nurseryStockUpdatePage.setCurrentQuantityInput('5');
        expect(nurseryStockUpdatePage.getCurrentQuantityInput()).toMatch('5');
        nurseryStockUpdatePage.setAddedQuantityInput('5');
        expect(nurseryStockUpdatePage.getAddedQuantityInput()).toMatch('5');
        nurseryStockUpdatePage.setConsumedQuantityInput('5');
        expect(nurseryStockUpdatePage.getConsumedQuantityInput()).toMatch('5');
        nurseryStockUpdatePage.setDescriptionInput('description');
        expect(nurseryStockUpdatePage.getDescriptionInput()).toMatch('description');
        nurseryStockUpdatePage.setStatusInput('5');
        expect(nurseryStockUpdatePage.getStatusInput()).toMatch('5');
        nurseryStockUpdatePage.setCreatedByInput('5');
        expect(nurseryStockUpdatePage.getCreatedByInput()).toMatch('5');
        nurseryStockUpdatePage.setModifiedByInput('5');
        expect(nurseryStockUpdatePage.getModifiedByInput()).toMatch('5');
        nurseryStockUpdatePage.setCreatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(nurseryStockUpdatePage.getCreatedAtInput()).toContain('2001-01-01T02:30');
        nurseryStockUpdatePage.setUpdatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(nurseryStockUpdatePage.getUpdatedAtInput()).toContain('2001-01-01T02:30');
        nurseryStockUpdatePage.nurserySelectLastOption();
        nurseryStockUpdatePage.pickListVarietySelectLastOption();
        nurseryStockUpdatePage.pickListCategorySelectLastOption();
        nurseryStockUpdatePage.save();
        expect(nurseryStockUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
