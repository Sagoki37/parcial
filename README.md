# parcial

class Producto:
    def __init__(self, nombre, cantidad):
        self.nombre = nombre
        self.inventario_inicial = cantidad
        self.cantidad_disponible = cantidad

    def vender(self, cantidad):
        if cantidad <= self.cantidad_disponible:
            self.cantidad_disponible -= cantidad
            print(f"Venta realizada: {cantidad} unidades de '{self.nombre}' vendidas.")
        else:
            print(f"No hay suficiente stock para vender {cantidad} unidades de '{self.nombre}'.")

    def verificar_disponibilidad(self):
        return self.cantidad_disponible > 0

    def duplicar_inventario_si_agotado(self):
        if self.cantidad_disponible == 0:
            self.cantidad_disponible = self.inventario_inicial * 2
            print(f"Inventario de '{self.nombre}' duplicado. Nueva cantidad: {self.cantidad_disponible}")
        else:
            print(f"'{self.nombre}' aún tiene stock. No se puede duplicar inventario.")

    def mostrar_info(self):
        print(f"Producto: {self.nombre}")
        print(f"Inventario Inicial: {self.inventario_inicial}")
        print(f"Cantidad Disponible: {self.cantidad_disponible}")
        print("-" * 30)


def main():
    inventario = {}

    while True:
        print("\n--- Menú de Inventario ---")
        print("1. Agregar producto")
        print("2. Registrar venta")
        print("3. Verificar disponibilidad")
        print("4. Duplicar inventario si está agotado")
        print("5. Mostrar inventario")
        print("6. Salir")

        opcion = input("Seleccione una opción: ")

        if opcion == "1":
            nombre = input("Ingrese el nombre del producto: ")
            cantidad = int(input("Ingrese la cantidad inicial: "))
            inventario[nombre] = Producto(nombre, cantidad)

        elif opcion == "2":
            nombre = input("Ingrese el nombre del producto: ")
            if nombre in inventario:
                cantidad = int(input("Ingrese la cantidad a vender: "))
                inventario[nombre].vender(cantidad)
            else:
                print("Producto no encontrado.")

        elif opcion == "3":
            nombre = input("Ingrese el nombre del producto: ")
            if nombre in inventario:
                disponible = inventario[nombre].verificar_disponibilidad()
                print(f"Disponibilidad: {'Sí' if disponible else 'No'}")
            else:
                print("Producto no encontrado.")

        elif opcion == "4":
            nombre = input("Ingrese el nombre del producto: ")
            if nombre in inventario:
                inventario[nombre].duplicar_inventario_si_agotado()
            else:
                print("Producto no encontrado.")

        elif opcion == "5":
            for producto in inventario.values():
                producto.mostrar_info()

        elif opcion == "6":
            print("Saliendo del programa.")
            break
        else:
            print("Opción inválida. Intente nuevamente.")


if __name__ == "__main__":
    main()
