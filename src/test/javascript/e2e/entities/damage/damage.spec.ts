import { browser, protractor } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { DamageComponentsPage, DamageUpdatePage } from './damage.page-object';

describe('Damage e2e test', () => {
    let navBarPage: NavBarPage;
    let damageUpdatePage: DamageUpdatePage;
    let damageComponentsPage: DamageComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Damages', () => {
        navBarPage.goToEntity('damage');
        damageComponentsPage = new DamageComponentsPage();
        expect(damageComponentsPage.getTitle()).toMatch(/projectGhApp.damage.home.title/);
    });

    it('should load create Damage page', () => {
        damageComponentsPage.clickOnCreateButton();
        damageUpdatePage = new DamageUpdatePage();
        expect(damageUpdatePage.getPageTitle()).toMatch(/projectGhApp.damage.home.createOrEditLabel/);
        damageUpdatePage.cancel();
    });

    it('should create and save Damages', () => {
        damageComponentsPage.clickOnCreateButton();
        damageUpdatePage.setNoOfQuantityInput('5');
        expect(damageUpdatePage.getNoOfQuantityInput()).toMatch('5');
        damageUpdatePage.setDateInput('2000-12-31');
        expect(damageUpdatePage.getDateInput()).toMatch('2000-12-31');
        damageUpdatePage.setStatusInput('5');
        expect(damageUpdatePage.getStatusInput()).toMatch('5');
        damageUpdatePage.setCreatedByInput('5');
        expect(damageUpdatePage.getCreatedByInput()).toMatch('5');
        damageUpdatePage.setModifiedByInput('5');
        expect(damageUpdatePage.getModifiedByInput()).toMatch('5');
        damageUpdatePage.setCreatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(damageUpdatePage.getCreatedAtInput()).toContain('2001-01-01T02:30');
        damageUpdatePage.setUpdatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(damageUpdatePage.getUpdatedAtInput()).toContain('2001-01-01T02:30');
        damageUpdatePage.batchSelectLastOption();
        damageUpdatePage.descriptionSelectLastOption();
        damageUpdatePage.save();
        expect(damageUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
