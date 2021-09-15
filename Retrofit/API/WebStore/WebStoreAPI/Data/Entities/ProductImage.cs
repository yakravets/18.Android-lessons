using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace WebStoreAPI.Data.Entities
{
    [Table("products_images")]
    public class ProductImage
    {
        public int Id { get; set; }

        [Required, StringLength(255)]
        public string Name { get; set; }
        public int Priority { get; set; }

        [ForeignKey("Product")]
        public int ProductId { get; set; }
        public virtual Product Product { get; set; }
    }
}
