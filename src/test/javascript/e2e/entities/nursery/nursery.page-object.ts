import { element, by, promise, ElementFinder } from 'protractor';

export class NurseryComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-nursery div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class NurseryUpdatePage {
    pageTitle = element(by.id('jhi-nursery-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nurseryNameInput = element(by.id('field_nurseryName'));
    nurseryAddressInput = element(by.id('field_nurseryAddress'));
    nurseryInchargeInput = element(by.id('field_nurseryIncharge'));
    statusInput = element(by.id('field_status'));
    createdByInput = element(by.id('field_createdBy'));
    modifiedByInput = element(by.id('field_modifiedBy'));
    createdAtInput = element(by.id('field_createdAt'));
    updatedAtInput = element(by.id('field_updatedAt'));
    sectorSelect = element(by.id('field_sector'));
    nurseryTypeSelect = element(by.id('field_nurseryType'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setNurseryNameInput(nurseryName): promise.Promise<void> {
        return this.nurseryNameInput.sendKeys(nurseryName);
    }

    getNurseryNameInput() {
        return this.nurseryNameInput.getAttribute('value');
    }

    setNurseryAddressInput(nurseryAddress): promise.Promise<void> {
        return this.nurseryAddressInput.sendKeys(nurseryAddress);
    }

    getNurseryAddressInput() {
        return this.nurseryAddressInput.getAttribute('value');
    }

    setNurseryInchargeInput(nurseryIncharge): promise.Promise<void> {
        return this.nurseryInchargeInput.sendKeys(nurseryIncharge);
    }

    getNurseryInchargeInput() {
        return this.nurseryInchargeInput.getAttribute('value');
    }

    setStatusInput(status): promise.Promise<void> {
        return this.statusInput.sendKeys(status);
    }

    getStatusInput() {
        return this.statusInput.getAttribute('value');
    }

    setCreatedByInput(createdBy): promise.Promise<void> {
        return this.createdByInput.sendKeys(createdBy);
    }

    getCreatedByInput() {
        return this.createdByInput.getAttribute('value');
    }

    setModifiedByInput(modifiedBy): promise.Promise<void> {
        return this.modifiedByInput.sendKeys(modifiedBy);
    }

    getModifiedByInput() {
        return this.modifiedByInput.getAttribute('value');
    }

    setCreatedAtInput(createdAt): promise.Promise<void> {
        return this.createdAtInput.sendKeys(createdAt);
    }

    getCreatedAtInput() {
        return this.createdAtInput.getAttribute('value');
    }

    setUpdatedAtInput(updatedAt): promise.Promise<void> {
        return this.updatedAtInput.sendKeys(updatedAt);
    }

    getUpdatedAtInput() {
        return this.updatedAtInput.getAttribute('value');
    }

    sectorSelectLastOption(): promise.Promise<void> {
        return this.sectorSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    sectorSelectOption(option): promise.Promise<void> {
        return this.sectorSelect.sendKeys(option);
    }

    getSectorSelect(): ElementFinder {
        return this.sectorSelect;
    }

    getSectorSelectedOption() {
        return this.sectorSelect.element(by.css('option:checked')).getText();
    }

    nurseryTypeSelectLastOption(): promise.Promise<void> {
        return this.nurseryTypeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    nurseryTypeSelectOption(option): promise.Promise<void> {
        return this.nurseryTypeSelect.sendKeys(option);
    }

    getNurseryTypeSelect(): ElementFinder {
        return this.nurseryTypeSelect;
    }

    getNurseryTypeSelectedOption() {
        return this.nurseryTypeSelect.element(by.css('option:checked')).getText();
    }

    save(): promise.Promise<void> {
        return this.saveButton.click();
    }

    cancel(): promise.Promise<void> {
        return this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}
