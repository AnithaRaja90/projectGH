import { browser, protractor } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { MotherBedComponentsPage, MotherBedUpdatePage } from './mother-bed.page-object';

describe('MotherBed e2e test', () => {
    let navBarPage: NavBarPage;
    let motherBedUpdatePage: MotherBedUpdatePage;
    let motherBedComponentsPage: MotherBedComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load MotherBeds', () => {
        navBarPage.goToEntity('mother-bed');
        motherBedComponentsPage = new MotherBedComponentsPage();
        expect(motherBedComponentsPage.getTitle()).toMatch(/projectGhApp.motherBed.home.title/);
    });

    it('should load create MotherBed page', () => {
        motherBedComponentsPage.clickOnCreateButton();
        motherBedUpdatePage = new MotherBedUpdatePage();
        expect(motherBedUpdatePage.getPageTitle()).toMatch(/projectGhApp.motherBed.home.createOrEditLabel/);
        motherBedUpdatePage.cancel();
    });

    /* it('should create and save MotherBeds', () => {
        motherBedComponentsPage.clickOnCreateButton();
        motherBedUpdatePage.setValueInput('5');
        expect(motherBedUpdatePage.getValueInput()).toMatch('5');
        motherBedUpdatePage.setStatusInput('5');
        expect(motherBedUpdatePage.getStatusInput()).toMatch('5');
        motherBedUpdatePage.setCreatedByInput('5');
        expect(motherBedUpdatePage.getCreatedByInput()).toMatch('5');
        motherBedUpdatePage.setModifiedByInput('5');
        expect(motherBedUpdatePage.getModifiedByInput()).toMatch('5');
        motherBedUpdatePage.setCreatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(motherBedUpdatePage.getCreatedAtInput()).toContain('2001-01-01T02:30');
        motherBedUpdatePage.setUpdatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(motherBedUpdatePage.getUpdatedAtInput()).toContain('2001-01-01T02:30');
        motherBedUpdatePage.nurserySelectLastOption();
        motherBedUpdatePage.save();
        expect(motherBedUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });*/

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
