import { browser, protractor } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { GodownComponentsPage, GodownUpdatePage } from './godown.page-object';

describe('Godown e2e test', () => {
    let navBarPage: NavBarPage;
    let godownUpdatePage: GodownUpdatePage;
    let godownComponentsPage: GodownComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Godowns', () => {
        navBarPage.goToEntity('godown');
        godownComponentsPage = new GodownComponentsPage();
        expect(godownComponentsPage.getTitle()).toMatch(/projectGhApp.godown.home.title/);
    });

    it('should load create Godown page', () => {
        godownComponentsPage.clickOnCreateButton();
        godownUpdatePage = new GodownUpdatePage();
        expect(godownUpdatePage.getPageTitle()).toMatch(/projectGhApp.godown.home.createOrEditLabel/);
        godownUpdatePage.cancel();
    });

    it('should create and save Godowns', () => {
        godownComponentsPage.clickOnCreateButton();
        godownUpdatePage.setNameInput('name');
        expect(godownUpdatePage.getNameInput()).toMatch('name');
        godownUpdatePage.setAddressInput('address');
        expect(godownUpdatePage.getAddressInput()).toMatch('address');
        godownUpdatePage.setInchargeInput('incharge');
        expect(godownUpdatePage.getInchargeInput()).toMatch('incharge');
        godownUpdatePage.setStatusInput('5');
        expect(godownUpdatePage.getStatusInput()).toMatch('5');
        godownUpdatePage.setCreatedByInput('5');
        expect(godownUpdatePage.getCreatedByInput()).toMatch('5');
        godownUpdatePage.setModifiedByInput('5');
        expect(godownUpdatePage.getModifiedByInput()).toMatch('5');
        godownUpdatePage.setCreatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(godownUpdatePage.getCreatedAtInput()).toContain('2001-01-01T02:30');
        godownUpdatePage.setUpdatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(godownUpdatePage.getUpdatedAtInput()).toContain('2001-01-01T02:30');
        godownUpdatePage.save();
        expect(godownUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
