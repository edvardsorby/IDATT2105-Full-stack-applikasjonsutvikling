let testUser;
describe('Admin page tests', () => {
    let testUser;
  
    before(async () => {
      const email = '1@gmail.com';
      const password = '123';
  
      try {
        const response = await getJwtToken(email, password);
        testUser = {
          email: email,
          token: response.data,
        };
      } catch (error) {
        console.error('Error getting test user token:', error);
      }
    });
  
    beforeEach(() => {
      cy.visit('http://localhost:5173/login');
      cy.get('#email').type("1@gmail.com");
      cy.get('#password').type("123");
      cy.get('.submit > button').click();
      cy.url().should('include', '/');
  
      cy.wait(1000)
    });


  it('should add and delete a category', () => {
    const newCategory = 'Test Category';
    cy.visit('http://localhost:5173/admin');
    cy.wait(5000)
    cy.get('input[type="text"]').type(newCategory);
    cy.get('button[type="submit"]').click();

    cy.wait(10000)
    cy.visit('http://localhost:5173/');
    cy.contains(newCategory).should('exist');

    cy.visit('http://localhost:5173/admin');

    cy.wait(1000)
    cy.get('.category-section li:contains("Test Category")')
    .find('.delete')
    .click()


    cy.visit('http://localhost:5173/');
    cy.contains(newCategory).should('not.exist');
  });
});
