package edu.brandeis.gps.rseg127.lms;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import edu.brandeis.gps.rseg127.lms.entities.Asset;
import edu.brandeis.gps.rseg127.lms.entities.Author;
import edu.brandeis.gps.rseg127.lms.repos.AssetRepo;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AssetTest {
    @Autowired
    AssetRepo assetRepo;

    // assets need to have title, isbn, and call number
    @Test(expected = DataIntegrityViolationException.class)
    public void createInvalidAsset() {
        // asset is missing a title
        Asset asset = new Asset();
        asset.setIsbn("1234");
        asset.setCallNumber("5678");
        // this should fail
        assetRepo.save(asset);
    }
    
    // asset is fine, author is not
    @Test(expected=DataIntegrityViolationException.class)
    public void createValidAssetWithInvalidAuthor() {
        // asset has the 3 required values
        Asset asset = new Asset();
        asset.setTitle("Cool Title");
        asset.setIsbn("1234");
        asset.setCallNumber("5678");
        // authors need to have a first name
        Author badAuthor = new Author();
        badAuthor.setMiddleName("Middle!");
        badAuthor.setLastName("Last!");
        // this one's ok but it should still break
        Author goodAuthor = new Author();
        goodAuthor.setFirstName("Cool Guy");
        // they must be in a set
        Set<Author> authors = new HashSet<Author>();
        authors.add(badAuthor);
        authors.add(goodAuthor);
        asset.setAuthors(authors);
        // this should fail
        assetRepo.save(asset);
    }

    // create an asset without any authors
    @Test
    public void createValidAssetWithNoAuthor() {
        // create an asset
        Asset asset = new Asset();
        asset.setTitle("Cool Title");
        asset.setIsbn("1234");
        asset.setCallNumber("5678");
        // persist it, capturing the returned asset
        Asset result = assetRepo.save(asset);
        // what did we get back?
        // checking if our asset matches the result
        assert asset.getTitle().equals(result.getTitle());
        assert asset.getIsbn().equals(result.getIsbn());
        assert asset.getCallNumber().equals(result.getCallNumber());
        assert asset.getAuthors().equals(result.getAuthors());
    }

    // create an asset with some authors
    @Test
    public void createValidAssetWithValidAuthors() {
        // asset has the 3 required values
        Asset asset = new Asset();
        asset.setTitle("Cool Title");
        asset.setIsbn("1234");
        asset.setCallNumber("5678");
        // create some authors
        Author author1 = new Author();
        author1.setFirstName("Cool");
        author1.setMiddleName("Author");
        author1.setLastName("Guy");
        // another
        Author author2 = new Author();
        author2.setFirstName("Cool Guy");
        // they must be in a set
        Set<Author> authors = new HashSet<Author>();
        authors.add(author1);
        authors.add(author2);
        asset.setAuthors(authors);
        // this should work
        assetRepo.save(asset);
    }

    // create an asset without authors, update to add authors later
    @Test
    public void updateValidAssetWithValidAuthors() {
        // asset has the 3 required values
        Asset asset = new Asset();
        asset.setTitle("Cool Title");
        asset.setIsbn("1234");
        asset.setCallNumber("5678");
        // create it with no authors
        Asset created = assetRepo.save(asset);
        // create some authors
        Author author1 = new Author();
        author1.setFirstName("Cool");
        author1.setMiddleName("Author");
        author1.setLastName("Guy");
        // another
        Author author2 = new Author();
        author2.setFirstName("Cool Guy");
        // they must be in a set
        Set<Author> authors = new HashSet<Author>();
        authors.add(author1);
        authors.add(author2);
        // add authors to the newly created asset
        created.setAuthors(authors);
        // then, save
        Asset updated = assetRepo.save(created);
        // assert that the created asset now matches updated one
        assert created.getTitle().equals(updated.getTitle());
        assert created.getIsbn().equals(updated.getIsbn());
        assert created.getCallNumber().equals(updated.getCallNumber());
        assertTrue(compareAuthorSetsIgnoringIds(created.getAuthors(), updated.getAuthors()));
    }

    // create an asset without any authors, then delete it
    @Test
    public void createValidAssetWithNoAuthorThenDelete() {
        // create an asset
        Asset asset = new Asset();
        asset.setTitle("Cool Title");
        asset.setIsbn("1234");
        asset.setCallNumber("5678");
        // persist it, capturing the returned asset
        Asset result = assetRepo.save(asset);
        // what did we get back?
        // checking if our asset matches the result
        assert asset.getTitle().equals(result.getTitle());
        assert asset.getIsbn().equals(result.getIsbn());
        assert asset.getCallNumber().equals(result.getCallNumber());
        assert asset.getAuthors().equals(result.getAuthors());
        // we got an id back?
        assertTrue(result.getId() > 0);
        // ok, now delete it
        assetRepo.deleteById(result.getId());
    }

    // we don't care about the insertion ids when comparing
    public Boolean compareAuthorSetsIgnoringIds(Set<Author> authors1, Set<Author> authors2) {
        // this is silly but set all ids to 0
        authors1.forEach(author -> {author.setId(0);});
        authors2.forEach(author -> {author.setId(0);});
        // this ensures the ids don't change the hash value
        return (authors1.hashCode() == authors2.hashCode());
    }
}