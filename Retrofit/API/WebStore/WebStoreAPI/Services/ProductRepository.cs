using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebStoreAPI.Data;
using WebStoreAPI.Data.Entities;

namespace WebStoreAPI.Repositories
{
    public class ProductRepository : IRepository<Product>
    {
        private bool disposed = false;
        private readonly _AppContext _AppContext;

        public ProductRepository(_AppContext appContext)
        {
            _AppContext = appContext;
        }

        public async Task CreateAsync(Product item)
        {
            await _AppContext.products.AddAsync(item);
        }

        public async Task DeleteAsync(int id)
        {
            Product product = await _AppContext.products.FindAsync(id);
            if (product != null)
            {
                _AppContext.products.Remove(product);
            }
        }

        public async Task<Product> GetAsync(int id)
        {
            return await _AppContext.products.FindAsync(id);
        }

        public IEnumerable<Product> GetAll()
        {
            return _AppContext.products.ToList();
        }

        public async Task SaveAsync()
        {
            await _AppContext.SaveChangesAsync();
        }

        protected virtual void Dispose(bool disposing)
        {
            if (!this.disposed)
            {
                if (disposing)
                {
                    _AppContext.Dispose();
                }
            }
            this.disposed = true;
        }
        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }

        async Task<Product> IRepository<Product>.GetAsync(int id)
        {
            return await _AppContext.products.FindAsync(id);
        }

        async Task IRepository<Product>.CreateAsync(Product item)
        {
            await _AppContext.products.AddAsync(item);
        }

        public void Update(Product item)
        {
            _AppContext.Entry(item).State = Microsoft.EntityFrameworkCore.EntityState.Modified;
        }
    }
}
