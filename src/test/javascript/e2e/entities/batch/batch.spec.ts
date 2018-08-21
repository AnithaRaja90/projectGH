import { browser, protractor } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { BatchComponentsPage, BatchUpdatePage } from './batch.page-object';

describe('Batch e2e test', () => {
    let navBarPage: NavBarPage;
    let batchUpdatePage: BatchUpdatePage;
    let batchComponentsPage: BatchComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Batches', () => {
        navBarPage.goToEntity('batch');
        batchComponentsPage = new BatchComponentsPage();
        expect(batchComponentsPage.getTitle()).toMatch(/projectGhApp.batch.home.title/);
    });

    it('should load create Batch page', () => {
        batchComponentsPage.clickOnCreateButton();
        batchUpdatePage = new BatchUpdatePage();
        expect(batchUpdatePage.getPageTitle()).toMatch(/projectGhApp.batch.home.createOrEditLabel/);
        batchUpdatePage.cancel();
    });

    it('should create and save Batches', () => {
        batchComponentsPage.clickOnCreateButton();
        batchUpdatePage.setBatchNoInput('batchNo');
        expect(batchUpdatePage.getBatchNoInput()).toMatch('batchNo');
        batchUpdatePage.setBatchNameInput('batchName');
        expect(batchUpdatePage.getBatchNameInput()).toMatch('batchName');
        batchUpdatePage.setQuantityInput('5');
        expect(batchUpdatePage.getQuantityInput()).toMatch('5');
        batchUpdatePage.setMotherBedInput('motherBed');
        expect(batchUpdatePage.getMotherBedInput()).toMatch('motherBed');
        batchUpdatePage.setShowingTypeInput('5');
        expect(batchUpdatePage.getShowingTypeInput()).toMatch('5');
        batchUpdatePage.setSowingDateInput('2000-12-31');
        expect(batchUpdatePage.getSowingDateInput()).toMatch('2000-12-31');
        batchUpdatePage.setClosedDateInput('2000-12-31');
        expect(batchUpdatePage.getClosedDateInput()).toMatch('2000-12-31');
        batchUpdatePage.setRoundInput('5');
        expect(batchUpdatePage.getRoundInput()).toMatch('5');
        batchUpdatePage.setRemarksInput('remarks');
        expect(batchUpdatePage.getRemarksInput()).toMatch('remarks');
        batchUpdatePage.setStatusInput('5');
        expect(batchUpdatePage.getStatusInput()).toMatch('5');
        batchUpdatePage.setCreatedByInput('5');
        expect(batchUpdatePage.getCreatedByInput()).toMatch('5');
        batchUpdatePage.setModifiedByInput('5');
        expect(batchUpdatePage.getModifiedByInput()).toMatch('5');
        batchUpdatePage.setCreatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(batchUpdatePage.getCreatedAtInput()).toContain('2001-01-01T02:30');
        batchUpdatePage.setUpdatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(batchUpdatePage.getUpdatedAtInput()).toContain('2001-01-01T02:30');
        batchUpdatePage.nurserySelectLastOption();
        batchUpdatePage.pickListVarietySelectLastOption();
        batchUpdatePage.pickListCategorySelectLastOption();
        batchUpdatePage.quantityTypeSelectLastOption();
        batchUpdatePage.save();
        expect(batchUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
